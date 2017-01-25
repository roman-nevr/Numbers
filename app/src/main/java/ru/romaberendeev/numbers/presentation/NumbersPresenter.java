package ru.romaberendeev.numbers.presentation;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ru.romaberendeev.numbers.android.App;
import ru.romaberendeev.numbers.android.custom_views.KeyPad;
import ru.romaberendeev.numbers.domain.StateKeeper;
import ru.romaberendeev.numbers.domain.entity.NumbersState;
import ru.romaberendeev.numbers.domain.interactor.AddDigitToAnswerInteractor;
import ru.romaberendeev.numbers.domain.interactor.ClearDigitInteractor;
import ru.romaberendeev.numbers.domain.interactor.CreateNewQuizInteractor;
import ru.romaberendeev.numbers.domain.interactor.IncreaseRightAnswersInteractor;
import ru.romaberendeev.numbers.domain.interactor.IncreaseTimeInteractor;
import ru.romaberendeev.numbers.domain.interactor.IncreaseWrongAnswersInteractor;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 23.01.2017.
 */

public class NumbersPresenter extends BasePresenter{
    private NumbersView view;
    private CompositeSubscription subscription;
    private int answer;
    @Inject StateKeeper<NumbersState> stateKeeper;
    @Inject IncreaseRightAnswersInteractor increaseRightAnswersInteractor;
    @Inject IncreaseWrongAnswersInteractor increaseWrongAnswersInteractor;
    @Inject CreateNewQuizInteractor newQuizInteractor;
    @Inject IncreaseTimeInteractor increaseTimeInteractor;
    @Inject ClearDigitInteractor clearDigitInteractor;
    @Inject AddDigitToAnswerInteractor addDigitToAnswerInteractor;

    @Inject
    public NumbersPresenter(NumbersView view) {
        this.view = view;
        subscription = new CompositeSubscription();
    }

    public void init(int difficulty) {
        boolean changed = stateKeeper.change(state ->{
            if(state != null){
                return false;
            }else {
                state = NumbersState.EMPTY;
                return true;
            }
        });
        System.out.println(new Date().getTime());
        if(stateKeeper.getValue() == NumbersState.EMPTY){
            newQuizInteractor.setDifficulty(difficulty).execute();
        }
        System.out.println(new Date().getTime());
    }

    @Override
    public void start() {
        System.out.println("start " + new Date().getTime());
        subscription.add(digitEvent());
        subscription.add(enterEvent());
        subscription.add(clearEvent());
        subscription.add(newAnswer());
        subscription.add(timer());
        subscription.add(newTime());
        subscription.add(newQuiz());
        subscription.add(newRightAnswer());
        subscription.add(newWrongAnswer());
    }

    private Subscription newRightAnswer() {
        return stateKeeper.getObservable()
                .map(state -> state.rightAnswers())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(number -> view.setRightAnswers(number));
    }

    private Subscription newWrongAnswer() {
        return stateKeeper.getObservable()
                .map(state -> state.wrongAnswers())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(number -> view.setWrongAnswers(number));
    }

    private Subscription newQuiz() {
        return stateKeeper.getObservable()
                .map(state -> state.quiz())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(quiz -> {
                    System.out.println("new quiz " + new Date().getTime());
                    view.setQuiz(quiz.quiz());
                });
    }

    private Subscription newTime() {
        return stateKeeper.getObservable()
                .map(state -> state.time())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> view.setTimer(time));
    }

    private Subscription timer() {
        return Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> increaseTimeInteractor.execute());
    }

    private Subscription newAnswer() {
        return stateKeeper.getObservable()
                .map(state -> state.playersAnswer())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(answer -> {
                    if(answer == 0){
                        view.setAnswer("");
                    }else {
                        view.setAnswer("" + answer);
                    }
                });
    }

    private Subscription clearEvent() {
        return view.getKeyPad().getObservable().filter(this::isClearCommand).subscribe(c -> clear());
    }

    private Boolean isClearCommand(Integer integer) {
        return integer == KeyPad.CLEAR;
    }

    private void clear() {
        clearDigitInteractor.execute();
    }

    private Subscription enterEvent() {
        return view.getKeyPad().getObservable()
                .filter(this::isEnterCommand)
                .subscribe(c -> enter());
    }

    private Boolean isEnterCommand(Integer integer) {
        return integer == KeyPad.ENTER;
    }

    private void enter() {
        if(isRightAnswer()){
            increaseRightAnswersInteractor.execute();
            newQuizInteractor.setDifficulty(1).execute();
            view.showRightAnimation();
        }else {
            increaseWrongAnswersInteractor.execute();
            newQuizInteractor.setDifficulty(1).execute();
            view.showWrongAnimation();
        }
    }

    private boolean isRightAnswer() {
        return stateKeeper.getValue().playersAnswer() == stateKeeper.getValue().quiz().answer();
    }

    private Subscription digitEvent() {
        System.out.println(view.getKeyPad());
        return view.getKeyPad().getObservable()
                .filter(this::isDigit)
                .subscribe(d -> addDigit(d));
    }

    private void addDigit(Integer d) {
        addDigitToAnswerInteractor.setDigit(d).execute();
    }

    private Boolean isDigit(Integer integer) {
        return integer >= 0 && integer < 10;
    }

    @Override
    public void stop() {
        subscription.clear();
    }

    public void exit(){
        ((App)view.provideContext()).clearNumbersComponent();
    }
}

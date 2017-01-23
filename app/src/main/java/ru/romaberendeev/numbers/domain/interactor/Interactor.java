package ru.romaberendeev.numbers.domain.interactor;


import rx.schedulers.Schedulers;

abstract public class Interactor {
    public void execute() {
        Schedulers.io().createWorker().schedule(this::operation);
    }

    public void executeSync() {
        operation();
    }

    abstract protected void operation();
}

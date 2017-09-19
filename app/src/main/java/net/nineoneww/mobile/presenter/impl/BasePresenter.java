package net.nineoneww.mobile.presenter.impl;

import net.nineoneww.mobile.ui.view.BaseView;

/**
 * Created by lilian on 2017/8/30.
 */

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    private T mBaseView;

    @Override
    public void attachView(T baseView) {
        mBaseView = baseView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public T getMvpView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}

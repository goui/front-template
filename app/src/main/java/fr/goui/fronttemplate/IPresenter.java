package fr.goui.fronttemplate;

/**
 * The basic interface for a presenter. All presenters should implement it.
 */
public interface IPresenter<T> {

    void attachView(T view);

    void detachView();
}

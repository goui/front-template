package fr.goui.fronttemplate;

import android.content.Context;

/**
 * The basic interface for a view. All views should implement it.
 */
public interface IView {

    Context getContext();

    void showProgressBar();

    void hideProgressBar();

    void showError(String message);
}

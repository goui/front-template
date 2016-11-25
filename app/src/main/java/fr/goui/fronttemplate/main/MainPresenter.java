package fr.goui.fronttemplate.main;

import fr.goui.fronttemplate.MyApplication;
import fr.goui.fronttemplate.network.NetworkService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Simple presenter getting a message on the backend.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView mView;

    /**
     * RxJava object.
     */
    private Subscription mSubscription;

    private String mMessage;

    @Override
    public void attachView(IMainView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    @Override
    public void load() {
        mView.showProgressBar();
        getMessage();
    }

    private void getMessage() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        MyApplication application = MyApplication.get(mView.getContext());
        NetworkService service = application.getNetworkService();
        mSubscription = service.getMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        mView.showResult(mMessage);
                        mView.hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(String message) {
                        mMessage = message;
                    }
                });
    }
}

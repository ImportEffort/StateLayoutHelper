package tips;


import android.view.View;

/**
 * @author wangshijia
 * @date 2018/04/12
 */
public interface TipsHelper {

    void showEmpty();

    void showEmpty(String description);

    void hideEmpty();

    void showLoading(boolean firstPage);

    void hideLoading();

    void showError(boolean firstPage, String errorMessage, View.OnClickListener l);

    void hideError();

}
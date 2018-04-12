package tips;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wangshijia.www.statelayouthelper.R;

/**
 * @author wangshijia
 * @date 2018/04/12
 */
public class DefaultTipsHelper implements TipsHelper {

    private final ProgressBar mLoadingView;
    private final View mView;
    private final Context mContext;

    public DefaultTipsHelper(Context context, View view) {
        this.mView = view;
        this.mContext = context;
        mLoadingView = new ProgressBar(context);
        mLoadingView.setPadding(0, DensityUtils.dip2px(context, 10),
                0, DensityUtils.dip2px(context, 10));
        mLoadingView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtils.dip2px(context, 40)));
    }

    @Override
    public void showEmpty() {
        if (mView == null)
            return;
        hideLoading();
        TipsUtils.showTips(mView, TipsType.EMPTY);
    }

    @Override
    public void showEmpty(String description) {
        if (mView == null)
            return;
        hideLoading();
        View tipsView = TipsUtils.showTips(mView, TipsType.EMPTY);
//        ImageView ivIcon = (ImageView) tipsView.findViewById(R.id.icon);
//        ivIcon.setImageResource(icon);
//        ivIcon.setOnClickListener(onClickListener);
        if (!TextUtils.isEmpty(description)) {
            ((TextView) tipsView.findViewById(R.id.description)).setText(description);
        }
    }

    @Override
    public void hideEmpty() {
        if (mView == null)
            return;
        TipsUtils.hideTips(mView, TipsType.EMPTY);
    }

    @Override
    public void showLoading(boolean firstPage) {
        if (mView == null)
            return;
        hideEmpty();
        hideError();
        if (firstPage) {
            TipsUtils.showTips(mView, TipsType.LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mView == null)
            return;
        hideEmpty();
        hideError();
        TipsUtils.hideTips(mView, TipsType.LOADING);
    }

    @Override
    public void showError(boolean firstPage, String errorMessage, View.OnClickListener onClickListener) {
        if (mView == null)
            return;
        hideLoading();
        if (firstPage) {
            View tipsView = TipsUtils.showTips(mView, TipsType.LOADING_FAILED);
            AppCompatButton retryBtn = (AppCompatButton) tipsView.findViewById(R.id.retry_btn);
            retryBtn.setOnClickListener(onClickListener);
            if (!TextUtils.isEmpty(errorMessage)) {
                ((TextView) tipsView.findViewById(R.id.description)).setText(errorMessage);
            }
        }
    }

    @Override
    public void hideError() {
        if (mView == null)
            return;
        TipsUtils.hideTips(mView, TipsType.LOADING_FAILED);
    }
}

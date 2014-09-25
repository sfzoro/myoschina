package net.oschina.app;

import com.my.studyandroid.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PullToRefreshListView extends ListView implements OnScrollListener
{
	private LayoutInflater inflater;  
	private LinearLayout headView;  
    private TextView tipsTextview;  
    private TextView lastUpdatedTextView;  
    private ImageView arrowImageView;  
    private ProgressBar progressBar; 
    
    // 用来设置箭头图标动画效果   
    private RotateAnimation animation;  
    private RotateAnimation reverseAnimation;  
    
    private int headContentWidth;  
    private int headContentHeight;  
    private int headContentOriginalTopPadding;
    
    private int firstItemIndex;  
    private int currentScrollState;
  
    
   
    
	public PullToRefreshListView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init(context);  
    }  

	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		 firstItemIndex = firstVisibleItem;  
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		currentScrollState = scrollState;
	}
	
	private void init(Context context) {   
    	//设置滑动效果
        animation = new RotateAnimation(0, -180,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);  
        animation.setInterpolator(new LinearInterpolator());  
        animation.setDuration(100);  
        animation.setFillAfter(true);  
  
        reverseAnimation = new RotateAnimation(-180, 0,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);  
        reverseAnimation.setInterpolator(new LinearInterpolator());  
        reverseAnimation.setDuration(100);  
        reverseAnimation.setFillAfter(true);  
        
        //获取布局
        inflater = LayoutInflater.from(context);  
        headView = (LinearLayout) inflater.inflate(R.layout.pull_to_refresh_head, null);  
	
        //初始化控件对象
        arrowImageView = (ImageView) headView.findViewById(R.id.head_arrowImageView);  
        arrowImageView.setMinimumWidth(50);  
        arrowImageView.setMinimumHeight(50); 
        progressBar = (ProgressBar) headView.findViewById(R.id.head_progressBar);  
        tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);  
        lastUpdatedTextView = (TextView) headView.findViewById(R.id.head_lastUpdatedTextView);  
        
        headContentOriginalTopPadding = headView.getPaddingTop();
	
        measureView(headView);  
        headContentHeight = headView.getMeasuredHeight();  
        headContentWidth = headView.getMeasuredWidth(); 
        
        headView.setPadding(headView.getPaddingLeft(), -1 * headContentHeight, headView.getPaddingRight(), headView.getPaddingBottom());  
        headView.invalidate();  

        System.out.println("初始高度："+headContentHeight); 
        System.out.println("初始TopPad："+headContentOriginalTopPadding);
        
        //把自定义的刷新列表头，放入列表前面
        addHeaderView(headView);        
        setOnScrollListener(this); 
	
	}
	
	  /**
	   *  计算headView的width及height值  
	   * @param child
	   * 不理解该算法
	   */
    private void measureView(View child) {  
        ViewGroup.LayoutParams p = child.getLayoutParams();  
        if (p == null) {  
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  
                    ViewGroup.LayoutParams.WRAP_CONTENT);  
        }  
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);  
        int lpHeight = p.height;  
        int childHeightSpec;  
        if (lpHeight > 0) {  
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,  
                    MeasureSpec.EXACTLY);  
        } else {  
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,  
                    MeasureSpec.UNSPECIFIED);  
        }  
        child.measure(childWidthSpec, childHeightSpec);  
    }  

	

}

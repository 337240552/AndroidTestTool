package com.example.alarmtest;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.coolcloud.uac.android.api.Coolcloud;
import com.coolcloud.uac.android.api.ErrInfo;
import com.coolcloud.uac.android.api.ErrorDescriptor;
import com.coolcloud.uac.android.api.auth.OAuth2.OnAuthListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class CoolmartDemo extends Activity implements OnClickListener {
	private TextView tvPrompt = null;
	private Button btnLogin = null;
	private Button btnWapLogin = null;
	private Button btnLogout = null;
	
	private static Handler handler = null;
	private Coolcloud coolcloud = null;
	
	private final static int MSG_WHAT_PROMPT = 0;
	
	private TextPool texlPool = new TextPool(5);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coolmart_main);
		
		tvPrompt = (TextView) findViewById(R.id.tvPrompt);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnWapLogin = (Button) findViewById(R.id.btnWapLogin);
		btnLogout = (Button) findViewById(R.id.btnLogout);
		
		btnLogin.setOnClickListener(this);
		btnWapLogin.setOnClickListener(this);
		btnLogout.setOnClickListener(this);
		
		handler = new THandler(this);
		coolcloud = Coolcloud.createInstance(this, "123456", "abcdef");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MainActivity", "[requestCode:" + requestCode + "][resultCode:"
				+ resultCode + "on activity result ...");
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnWapLogin:
			coolcloud.login(this, "all", new OnAuthListener() {
				@Override
				public void onCancel() {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "登录过程被取消");
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onDone(Object arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
                                        // arg0转换为Bundle对象

					Bundle data = new Bundle();
					data.putString("what", "登录成功: " + arg0);
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onError(ErrInfo arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "登录发生错误：" + arg0.getError());
					message.setData(data);
					handler.sendMessage(message);
				}
			});
		case R.id.btnLogin:
			coolcloud.login(this, "all", new OnAuthListener() {
				@Override
				public void onCancel() {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "登录过程被取消");
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onDone(Object arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "登录成功: " + arg0);
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onError(ErrInfo arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "登录发生错误：" + arg0.getError());
					message.setData(data);
					handler.sendMessage(message);
				}

				
			});
			break;
		case R.id.btnLogout:
//			try {
//				Intent i = new Intent();
//				i.setAction("com.coolcloud.sync");
//				this.startActivityForResult(i, 1005);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			coolcloud.logout(this, new OnAuthListener() {

				@Override
				public void onCancel() {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "退出登录过程被取消");
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onDone(Object arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "退出登录成功");
					message.setData(data);
					handler.sendMessage(message);
				}

				@Override
				public void onError(ErrInfo arg0) {
					Message message = new Message();
					message.what = MSG_WHAT_PROMPT;
					Bundle data = new Bundle();
					data.putString("what", "退出登录发生错误");
					message.setData(data);
					handler.sendMessage(message);
				}

				 
			});
			break;
		default:
			break;
		}
	}
	
	private void onHandleMessage(Message msg) {
		switch (msg.what) {
		case MSG_WHAT_PROMPT:
			texlPool.add(msg.getData().getString("what"));
			tvPrompt.setText(texlPool.toString());
			break;
		default:
			break;
		}
	}
	
	public static class TextPool {
    	private static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS",
        		Locale.getDefault());
    	private int maxLineSize = 10;
    	private List<String> texts = new ArrayList<String>();
    	
    	public TextPool(int maxLineSize) {
    		this.maxLineSize = maxLineSize;
    	}
    	
    	public TextPool add(String s) {
    		synchronized (texts) {
    			texts.add(formatter.format(new Date()) + " " + s);
    			
    			while (texts.size() > maxLineSize) {
    				texts.remove(0);
    			}
    		}
    		
    		return this;
    	}
    	
    	public void reset() {
    		synchronized (texts) {
    			texts.clear();
    		}
    	}
    	
    	public String toString() {
    		boolean first = true;
    		StringBuffer sb = new StringBuffer();
    		
    		synchronized (texts) {
    			for (String text: texts) {
    				if (first) {
    					first = false;
    					sb.append(text);
    				} else {
    					sb.append("\n").append(text);
    				}
    			}
    		}
    		
    		return sb.toString();
    	}
    }
	
	/**
	 * 消息处理器
	 * @author zhangheng
	 *
	 */
	private static class THandler extends Handler {
		/**
		 * 弱引用
		 */
		WeakReference<CoolmartDemo> reference = null;
		
		/**
		 * 构造函数
		 * @param reference 界面对象引用
		 */
		public THandler(CoolmartDemo activity) {
			this.reference = new WeakReference<CoolmartDemo>(activity);
		}
		
		@Override
        public void handleMessage(Message msg) {
			CoolmartDemo activity = reference.get();
			
			if (null != activity) {
				activity.onHandleMessage(msg);
			}
        }
    };
}

package linj.lib.rnnavbarstyle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import android.graphics.Color;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.react.uimanager.IllegalViewOperationException;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

public class RNNavigationBarStyleModule extends ReactContextBaseJavaModule {

	private final ReactApplicationContext reactContext;

	public int backupFlags = 0;

	public RNNavigationBarStyleModule(ReactApplicationContext reactContext) {
		super(reactContext);
		this.reactContext = reactContext;
	}

	@Override
	public String getName() {
		return "RNNavigationBarStyle";
	}

	@ReactMethod
	public void setHidden(final Boolean hidden, Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						View decorView = getCurrentActivity().getWindow().getDecorView();

						if (hidden) {
							backupFlags = decorView.getSystemUiVisibility();

							int UI_FLAG_HIDE_NAV_BAR = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
								| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
								| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
								| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

							decorView.setSystemUiVisibility(UI_FLAG_HIDE_NAV_BAR);
						}
						else {
							decorView.setSystemUiVisibility(backupFlags);
						}
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}

	@ReactMethod
	public void setLightNav(final Boolean light, final Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						View decorView = getCurrentActivity().getWindow().getDecorView();

						int flags = decorView.getSystemUiVisibility();
						if (!light) {
							flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
						}
						else {
							flags &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
						}

						decorView.setSystemUiVisibility(flags);
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}

	@ReactMethod
	public void setTranslucent(final Boolean translucent, Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						View decorView = getCurrentActivity().getWindow().getDecorView();

						int flags = decorView.getSystemUiVisibility();
						if (translucent) {
							flags |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
						}
						else {
							flags &= ~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
						}

						decorView.setSystemUiVisibility(flags);
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}

	@ReactMethod
	public void setBackgroundColor(final String color, final Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						Integer colorTo = Color.parseColor(String.valueOf(color));
						final Window window = getCurrentActivity().getWindow();
						window.setNavigationBarColor(colorTo);

						WritableMap map = Arguments.createMap();
						map.putBoolean("success", true);
						promise.resolve(map);
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}

	@ReactMethod
	public void getSystemUiVisibility(final Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						View decorView = getCurrentActivity().getWindow().getDecorView();

						int flags = decorView.getSystemUiVisibility();
						promise.resolve(flags);
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}

	@ReactMethod
	public void setSystemUiVisibility(final int flag, Promise promise) {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (getCurrentActivity() != null) {
						View decorView = getCurrentActivity().getWindow().getDecorView();

						decorView.setSystemUiVisibility(flag);
					}
				}
			});
		}
		catch (IllegalViewOperationException e) {
			WritableMap map = Arguments.createMap();
			map.putBoolean("success", false);
			promise.reject("error", e);
		}
	}
}
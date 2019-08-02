package com.midas.demo.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.midas.demo.api.MyApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: yuhaibo
 * @time: 2018/12/10 下午7:11.
 * projectName: YBMMarket.
 * Description: 吐司工具类
 */
public class ToastUtils {

    private static Toast sToast;
    private static int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private static int xOffset = 0;
    private static int yOffset = (int) (64 * MyApplication.getContext().getResources().getDisplayMetrics().density + 0.5);
    private static View customView;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 设置吐司位置
     *
     * @param gravity 位置
     * @param xOffset x偏移
     * @param yOffset y偏移
     */
    public static void setGravity(int gravity, int xOffset, int yOffset) {
        ToastUtils.gravity = gravity;
        ToastUtils.xOffset = xOffset;
        ToastUtils.yOffset = yOffset;
    }

    /**
     * 设置吐司view
     *
     * @param layoutId 视图
     */
    public static void setView(@LayoutRes int layoutId) {
        LayoutInflater inflate = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ToastUtils.customView = inflate.inflate(layoutId, null);
    }

    /**
     * 设置吐司view
     *
     * @param view 视图
     */
    public static void setView(View view) {
        ToastUtils.customView = view;
    }

    /**
     * 获取吐司view
     *
     * @return view 自定义view
     */
    public static View getView() {
        if (customView != null) return customView;
        if (sToast != null) return sToast.getView();
        return null;
    }

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortSafe(final CharSequence text) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(text, Toast.LENGTH_SHORT);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortSafe(final @StringRes int resId) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(resId, Toast.LENGTH_SHORT);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortSafe(final @StringRes int resId, final Object... args) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(resId, Toast.LENGTH_SHORT, args);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortSafe(final String format, final Object... args) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(format, Toast.LENGTH_SHORT, args);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongSafe(final CharSequence text) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(text, Toast.LENGTH_LONG);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLongSafe(final @StringRes int resId) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(resId, Toast.LENGTH_LONG);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLongSafe(final @StringRes int resId, final Object... args) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(resId, Toast.LENGTH_LONG, args);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLongSafe(final String format, final Object... args) {
        try {
            sHandler.post(new Runnable() {
                @Override
                public void run() {
                    show(format, Toast.LENGTH_LONG, args);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    public static void showShort(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;
        show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShort(@StringRes int resId) {
        show(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShort(@StringRes int resId, Object... args) {
        show(resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShort(String format, Object... args) {
        if (TextUtils.isEmpty(format)) {
            show(format, Toast.LENGTH_SHORT);
        } else {
            show(format, Toast.LENGTH_SHORT, args);
        }
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLong(CharSequence text) {
        show(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     */
    public static void showLong(@StringRes int resId) {
        show(resId, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showLong(@StringRes int resId, Object... args) {
        show(resId, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showLong(String format, Object... args) {
        show(format, Toast.LENGTH_LONG, args);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private static void show(@StringRes int resId, int duration) {
        show(MyApplication.getContext().getResources().getText(resId).toString(), duration);
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     * @param args     参数
     */
    private static void show(@StringRes int resId, int duration, Object... args) {
        show(String.format(MyApplication.getContext().getResources().getString(resId), args), duration);
    }

    /**
     * 显示吐司
     *
     * @param format   格式
     * @param duration 显示时长
     * @param args     参数
     */
    private static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }

    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private static void show(CharSequence text, int duration) {
        try {
            if (TextUtils.isEmpty(text))
                return;
            cancel();
            if (customView != null) {
                sToast = new Toast(MyApplication.getContext());
                sToast.setView(customView);
                sToast.setDuration(duration);
            } else {
                sToast = Toast.makeText(MyApplication.getContext(), text, duration);
            }
           // sToast.setGravity(gravity, xOffset, yOffset);
            sToast.setGravity(Gravity.CENTER,0,0);
            if (isNotificationEnabled()) {  //检测系统通知功能是否可用
                sToast.show();
            } else {
                showToastWhenNotificationDisable(sToast);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消吐司显示
     */
    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    private static Object obj;

    /**
     * 用户禁用系统通知后Tosat无法正常显示时调用
     *
     * @param toast
     */
    private static void showToastWhenNotificationDisable(Toast toast) {
        try {
            Method declaredMethod = Toast.class.getDeclaredMethod("getService");
            declaredMethod.setAccessible(true);
            if (obj == null) {
                obj = declaredMethod.invoke(null);
                Class mINotificationManagerClass = Class.forName("android.app.INotificationManager");
                Object iNotificationProxy = Proxy.newProxyInstance(toast.getClass().getClassLoader(), new Class[]{mINotificationManagerClass}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if ("enqueueToast".equals(method.getName()) || "enqueueToastEx".equals(method.getName())) {
                            args[0] = "android";
                        }
                        return method.invoke(obj, args);
                    }
                });
                Field declaredField = Toast.class.getDeclaredField("sService");
                declaredField.setAccessible(true);
                declaredField.set(null, iNotificationProxy);
            }
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断系统通知是否可用
     *
     * @return
     */
    private static boolean isNotificationEnabled() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MyApplication.getContext());
        return notificationManagerCompat.areNotificationsEnabled();
    }
}

package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
    private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    /* access modifiers changed from: package-private */
    public static class OnRequeryFactory {
        OnRequeryFactory() {
        }

        /* access modifiers changed from: package-private */
        public void onRequery(ViewModel viewModel) {
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class KeyedFactory extends OnRequeryFactory implements Factory {
        public abstract <T extends ViewModel> T create(String str, Class<T> cls);

        KeyedFactory() {
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public ViewModelProvider(ViewModelStoreOwner owner) {
        this(r0, r1);
        Factory factory;
        ViewModelStore viewModelStore = owner.getViewModelStore();
        if (owner instanceof HasDefaultViewModelProviderFactory) {
            factory = ((HasDefaultViewModelProviderFactory) owner).getDefaultViewModelProviderFactory();
        } else {
            factory = NewInstanceFactory.getInstance();
        }
    }

    public ViewModelProvider(ViewModelStoreOwner owner, Factory factory) {
        this(owner.getViewModelStore(), factory);
    }

    public ViewModelProvider(ViewModelStore store, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = store;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            return (T) get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends ViewModel> T get(String key, Class<T> modelClass) {
        T t = (T) this.mViewModelStore.get(key);
        if (modelClass.isInstance(t)) {
            Factory factory = this.mFactory;
            if (factory instanceof OnRequeryFactory) {
                ((OnRequeryFactory) factory).onRequery(t);
            }
            return t;
        }
        Factory factory2 = this.mFactory;
        ViewModel viewModel = factory2 instanceof KeyedFactory ? (T) ((KeyedFactory) factory2).create(key, modelClass) : (T) factory2.create(modelClass);
        this.mViewModelStore.put(key, viewModel);
        return (T) viewModel;
    }

    public static class NewInstanceFactory implements Factory {
        private static NewInstanceFactory sInstance;

        static NewInstanceFactory getInstance() {
            if (sInstance == null) {
                sInstance = new NewInstanceFactory();
            }
            return sInstance;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            try {
                return modelClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e2);
            }
        }
    }

    public static class AndroidViewModelFactory extends NewInstanceFactory {
        private static AndroidViewModelFactory sInstance;
        private Application mApplication;

        public static AndroidViewModelFactory getInstance(Application application) {
            if (sInstance == null) {
                sInstance = new AndroidViewModelFactory(application);
            }
            return sInstance;
        }

        public AndroidViewModelFactory(Application application) {
            this.mApplication = application;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory, androidx.lifecycle.ViewModelProvider.NewInstanceFactory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (!AndroidViewModel.class.isAssignableFrom(modelClass)) {
                return (T) super.create(modelClass);
            }
            try {
                return modelClass.getConstructor(Application.class).newInstance(this.mApplication);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e3);
            } catch (InvocationTargetException e4) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e4);
            }
        }
    }
}

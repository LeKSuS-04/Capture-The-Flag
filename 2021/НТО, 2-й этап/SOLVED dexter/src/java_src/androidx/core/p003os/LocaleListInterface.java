package androidx.core.p003os;

import java.util.Locale;

/* access modifiers changed from: package-private */
/* renamed from: androidx.core.os.LocaleListInterface */
public interface LocaleListInterface {
    Locale get(int i);

    Locale getFirstMatch(String[] strArr);

    Object getLocaleList();

    int indexOf(Locale locale);

    boolean isEmpty();

    int size();

    String toLanguageTags();
}

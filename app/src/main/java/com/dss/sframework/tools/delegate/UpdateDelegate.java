package com.dss.sframework.tools.delegate;

import android.content.Context;

public interface UpdateDelegate {
    void sucessoUpdate(boolean sucesso);
    void ErroUpdate(Exception e);
    Context getContext();
}

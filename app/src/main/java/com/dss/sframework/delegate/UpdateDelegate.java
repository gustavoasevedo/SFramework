package com.dss.sframework.delegate;

import android.content.Context;

public interface UpdateDelegate {
    void sucessoUpdate(boolean sucesso);
    void ErroUpdate(Exception e);
    Context getContext();
}

package com.dss.sframework.delegate;

import android.content.Context;
import android.widget.TextView;

public interface UpdateDelegate {
    void sucessoUpdate(boolean sucesso);
    void ErroUpdate(Exception e);
    Context getContext();
    TextView getTVTitulo();
    TextView getTVPorcentagem();
}

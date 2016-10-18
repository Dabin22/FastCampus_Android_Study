package com.leedabin.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Dabin on 2016-10-17.
 */
public class PreviewStage extends View {
    private int[][] previewMap;
    private int[][] block;
    public PreviewStage(Context context, int[][] previewMap, int[][] block) {
        super(context);
        this.previewMap = previewMap;
        this.block = block;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //TODO 2. 프리뷰 영역을 그린다.
        //2.1 항상 다음에 보여줄 블럭과 함께 그린다.

    }
}

package com.lingc.readrecord;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;

public class AboutActivity extends MaterialAboutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_about);
        getMaterialAboutList(this);
    }

    /*public static MaterialAboutList createMaterialAboutList(final Context c, final int colorIcon, final int theme) {


    }*/

    @NonNull
    @Override
    protected MaterialAboutList getMaterialAboutList(@NonNull Context context) {

        /*MaterialAboutCard.Builder builder = new MaterialAboutCard.Builder();
        builder.addItem(new MaterialAboutTitleItem.Builder()
                .text("书亦")
                .icon(R.mipmap.ic_launcher_lingc)
                .build());

        builder.addItem(new MaterialAboutActionItem.Builder()
                .text("版本")
                .subText("1.0.1")
                .setOnClickAction(new MaterialAboutItemOnClickAction() {
                    @Override
                    public void onClick() {

                    }
                })
                .build());*/

        MaterialAboutCard card = new MaterialAboutCard.Builder()
                .title("关于")
                .addItem(new MaterialAboutTitleItem.Builder()
                        .text("书亦")
                        .icon(R.mipmap.ic_launcher)
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("版本")
                        .subText("v1.0.1 (查看更新日志)")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HelloLingC/readrecord/blob/master/README.md"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("开发者")
                        .subText("LingC")
                        .build())
                .build();
        MaterialAboutCard card1 = new MaterialAboutCard.Builder()
                .title("感谢以下开源项目")
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("material-about-library")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("Glide")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("CircleImageView")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("OkHttp")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("LitePal")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {

                            }
                        })
                        .build())
                .build();

        return new MaterialAboutList.Builder()
                .addCard(card)
                .addCard(card1)
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.about_name);
    }

}

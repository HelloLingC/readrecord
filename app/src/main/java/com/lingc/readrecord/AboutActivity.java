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
                        .icon(R.drawable.baseline_error_outline_24)
                        .subText("1.1.0 (查看更新日志)")
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
                        .icon(R.drawable.baseline_perm_identity_24)
                        .subText("LingC")
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("博客")
                        .icon(R.drawable.baseline_language_24)
                        .subText("https://lcblog.cn")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lcblog.cn"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("源代码")
                        .icon(R.drawable.baseline_language_24)
                        .subText("https://github.com/HelloLingC/readrecord")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HelloLingC/readrecord"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .build();

        /*MaterialAboutCard authorCard = new MaterialAboutCard.Builder()
                .title("开发者")
                .addItem(new MaterialAboutTitleItem.Builder()
                        .text("LingC")
                        .icon(R.mipmap.ic_lingc)
                        .build())

                .build();*/

        MaterialAboutCard thinksCard = new MaterialAboutCard.Builder()
                .title("感谢以下开源项目")
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("material-about-library")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daniel-stoneuk/material-about-library"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("Glide")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/bumptech/glide"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("CircleImageView")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hdodenhof/CircleImageView"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("OkHttp")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/okhttp"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .addItem(new MaterialAboutActionItem.Builder()
                        .text("LitePal")
                        .setOnClickAction(new MaterialAboutItemOnClickAction() {
                            @Override
                            public void onClick() {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LitePalFramework/LitePal"));
                                startActivity(intent);
                            }
                        })
                        .build())
                .build();

        return new MaterialAboutList.Builder()
                .addCard(card)
                .addCard(thinksCard)
                .build();
    }

    @Nullable
    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.about_name);
    }

}

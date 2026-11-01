
package br.com.pokehub.firetv;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout content;
    private int playerHP = 100;
    private int rivalHP = 100;
    private int wins = 0;
    private String companion = "Pikachu";

    private final String[] pokemon = {
        "Pikachu ⚡", "Charmander 🔥", "Bulbasaur 🌿",
        "Squirtle 💧", "Eevee ⭐", "Gengar 👻",
        "Snorlax 💤", "Lucario 💥"
    };

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        showHome();
    }

    private int dp(int n) {
        return (int) (n * getResources().getDisplayMetrics().density);
    }

    private TextView text(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(Color.WHITE);
        t.setGravity(Gravity.CENTER);
        t.setPadding(dp(8), dp(8), dp(8), dp(8));
        return t;
    }

    private Button button(String label, Runnable action) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(17);
        b.setAllCaps(false);
        b.setFocusable(true);
        b.setOnClickListener(v -> action.run());
        return b;
    }

    private void base(String title) {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(18, 24, 52));

        TextView heading = text(title, 27);
        heading.setTypeface(null, Typeface.BOLD);
        heading.setBackgroundColor(Color.rgb(30, 100, 180));
        root.addView(heading);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER_HORIZONTAL);
        content.setPadding(dp(14), dp(12), dp(14), dp(20));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
            -1, 0, 1
        ));

        setContentView(root);
    }

    private void showHome() {
        base("⚡ POKEHUB FIRE TV ⚡");

        content.addView(text("Sua aventura Pokémon começa aqui!", 21));
        content.addView(text("Companheiro atual: " + companion, 18));

        addButton("📘 Pokédex", this::showPokedex);
        addButton("⚔️ Batalha Pokémon", this::showBattle);
        addButton("💛 Meu companheiro", this::showCompanion);
        addButton("🏆 Coleção e conquistas", this::showCollection);
        addButton("🗺️ Explorar regiões", this::showMap);
        addButton("📺 Aplicativos instalados", this::showApps);
    }

    private void addButton(String label, Runnable action) {
        Button b = button(label, action);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
            -1, -2
        );
        p.setMargins(dp(4), dp(5), dp(4), dp(5));
        content.addView(b, p);
    }

    private void showPokedex() {
        base("📘 POKÉDEX");

        content.addView(text(
            "Escolha um Pokémon para conhecer:", 19
        ));

        for (String p : pokemon) {
            addButton(p, () -> {
                Toast.makeText(this,
                    "Você selecionou " + p,
                    Toast.LENGTH_SHORT).show();
            });
        }

        addButton("⬅ Voltar", this::showHome);
    }

    private void showBattle() {
        base("⚔️ ARENA POKÉMON");
        content.addView(text("Você: " + companion, 22));
        content.addView(text("Seu HP: " + playerHP, 20));
        content.addView(text("Rival: Pokémon adversário", 20));
        content.addView(text("HP do rival: " + rivalHP, 20));

        addButton("⚡ Atacar", () -> {
            if (playerHP <= 0 || rivalHP <= 0) {
                Toast.makeText(this,
                    "Comece uma nova batalha!",
                    Toast.LENGTH_SHORT).show();
                return;
            }

            rivalHP -= 25;

            if (rivalHP <= 0) {
                rivalHP = 0;
                wins++;
                Toast.makeText(this,
                    "Vitória! Você ganhou!",
                    Toast.LENGTH_LONG).show();
            } else {
                playerHP -= 15;
                if (playerHP < 0) playerHP = 0;
            }

            showBattle();
        });

        addButton("🔄 Nova batalha", () -> {
            playerHP = 100;
            rivalHP = 100;
            showBattle();
        });

        addButton("⬅ Voltar", this::showHome);
    }

    private void showCompanion() {
        base("💛 COMPANHEIRO");

        content.addView(text(
            "Escolha quem vai acompanhar você!", 20
        ));

        for (String p : pokemon) {
            addButton(p, () -> {
                companion = p;
                Toast.makeText(this,
                    companion + " agora é seu companheiro!",
                    Toast.LENGTH_SHORT).show();
                showCompanion();
            });
        }

        addButton("⬅ Voltar", this::showHome);
    }

    private void showCollection() {
        base("🏆 MINHA COLEÇÃO");
        content.addView(text("Vitórias: " + wins, 23));
        content.addView(text(
            "Companheiro: " + companion, 20
        ));
        content.addView(text(
            "Continue vencendo batalhas para melhorar seu recorde!",
            17
        ));
        addButton("⬅ Voltar", this::showHome);
    }

    private void showMap() {
        base("🗺️ EXPLORAR REGIÕES");

        String[] regions = {
            "Kanto", "Johto", "Hoenn", "Sinnoh",
            "Unova", "Kalos", "Alola", "Paldea"
        };

        for (String region : regions) {
            addButton("🌎 " + region, () ->
                Toast.makeText(this,
                    "Você está explorando " + region + "!",
                    Toast.LENGTH_SHORT).show()
            );
        }

        addButton("⬅ Voltar", this::showHome);
    }

    private void showApps() {
        base("📺 APLICATIVOS");

        android.content.pm.PackageManager pm =
            getPackageManager();

        java.util.List<android.content.pm.ResolveInfo> apps =
            pm.queryIntentActivities(
                new android.content.Intent(
                    android.content.Intent.ACTION_MAIN
                ).addCategory(
                    android.content.Intent.CATEGORY_LEANBACK_LAUNCHER
                ), 0
            );

        if (apps.isEmpty()) {
            content.addView(text(
                "Não foram encontrados atalhos de TV.", 18
            ));
        } else {
            for (android.content.pm.ResolveInfo info : apps) {
                String label = info.loadLabel(pm).toString();

                addButton("📺 " + label, () -> {
                    android.content.Intent launch =
                        pm.getLeanbackLaunchIntentForPackage(
                            info.activityInfo.packageName
                        );

                    if (launch == null) {
                        launch = pm.getLaunchIntentForPackage(
                            info.activityInfo.packageName
                        );
                    }

                    if (launch != null) startActivity(launch);
                    else Toast.makeText(this,
                        "Não foi possível abrir este aplicativo.",
                        Toast.LENGTH_SHORT).show();
                });
            }
        }

        addButton("⬅ Voltar", this::showHome);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showHome();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

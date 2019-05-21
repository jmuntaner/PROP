package views;

import controllers.ControladorPartida;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.interrupted;

public class VistaJugar extends VistaAmbTauler {
    private long iniciTorn, iniciPartida;
    private final ScheduledExecutorService executorService;
    private JLabel labelTempsTorn, labelTempsPatida, labelTorn, labelEscac;
    private ControladorPartida cp;
    private ScheduledFuture fut;
    private boolean movimentIniciat;
    private ArrayList<Pair<Integer, Integer>> movs;
    private Pair<Integer, Integer> posIni;
    private Thread tMaquina;

    @Override
    JPanel getPanelBotons() {
        JPanel panelBotonsJugar = new JPanel(new GridBagLayout());
        JButton enrere = new JButton("Tornar");
        enrere.addActionListener(e -> {
            if (tMaquina != null) {
                tMaquina.interrupt();

            }
            vp.mostraLlistaProblemes();

        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(4, 4, 0, 0);

        panelBotonsJugar.add(enrere, gbc);
        return panelBotonsJugar;
    }

    VistaJugar(VistaPrincipal vp) {
        super(vp);
        iniciPartida = System.currentTimeMillis();
        iniciTorn = iniciPartida;
        executorService = Executors.newSingleThreadScheduledExecutor();
        movimentIniciat = false;
        initDisplays();
    }

    /**
     * Assigna un controlador a la partida
     *
     * @param c Controlador a assignar
     */
    void setControlador(ControladorPartida c) {
        cp = c;
        reloadTauler();
        movimentIniciat = false;

        // Reinicia el timer
        iniciPartida = System.currentTimeMillis();
        iniciTorn = iniciPartida;
        if (fut != null) fut.cancel(true);
        labelTempsTorn.setText("00:00:00");
        labelTempsPatida.setText("00:00:00");
        fut = executorService.scheduleWithFixedDelay(
                this::updateTimer, 1, 1, TimeUnit.SECONDS);
        // Actualitza panel
        updateData();
        labelEscac.setEnabled(false);
        setInteractable(true);
        tornMaquina();
    }

    /**
     * Recarrega totes les caselles del tauler
     */
    private void reloadTauler() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                setPos(i, j, cp.getPos(i, j));
                desmarcaPos(i, j);
            }
    }

    /**
     * Crea el panel dret d'informació
     *
     * @return Panel d'informació.
     */
    @Override
    JPanel getPanelDreta() {
        JPanel panelInfo = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panelInfo.setLayout(gbl);

        GridBagConstraints gbc_info = new GridBagConstraints();

        // Temps de torn
        gbc_info.gridx = 0;
        gbc_info.gridy = 0;
        gbc_info.gridwidth = 2;
        gbc_info.fill = GridBagConstraints.HORIZONTAL;
        labelTempsTorn = new JLabel("00:00:00");
        Font defaulltFont = labelTempsTorn.getFont();
        labelTempsTorn.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 40));
        labelTempsTorn.setBorder(BorderFactory.createTitledBorder("Temps torn"));
        panelInfo.add(labelTempsTorn, gbc_info);

        // Temps total
        gbc_info.gridwidth = 1;
        gbc_info.weightx = 1;
        gbc_info.gridy++;
        gbc_info.insets = new Insets(0, 4, 0, 0);
        panelInfo.add(new JLabel("Temps total:"), gbc_info);
        gbc_info.insets = new Insets(0, 0, 0, 4);
        gbc_info.fill = GridBagConstraints.NONE;
        gbc_info.gridx = 1;
        gbc_info.anchor = GridBagConstraints.LINE_END;
        labelTempsPatida = new JLabel("00:00:00");
        panelInfo.add(labelTempsPatida, gbc_info);

        // Nom del jugador
        gbc_info.gridy++;
        gbc_info.gridx = 0;
        gbc_info.gridwidth = 2;
        gbc_info.fill = GridBagConstraints.HORIZONTAL;
        labelTorn = new JLabel("Mou - (-)");
        labelTorn.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 14));
        panelInfo.add(labelTorn, gbc_info);

        // Glue
        gbc_info.gridy++;
        gbc_info.weighty = 1;
        gbc_info.fill = GridBagConstraints.BOTH;
        panelInfo.add(Box.createGlue(), gbc_info);

        gbc_info.gridy++;
        gbc_info.weighty = 0;
        gbc_info.fill = GridBagConstraints.HORIZONTAL;
        labelEscac = new JLabel("Escac!");
        labelEscac.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 20));
        labelEscac.setVisible(false);
        panelInfo.add(labelEscac, gbc_info);
        // Posa un borde senzill al panel
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panelInfo;
    }

    /**
     * Actualitza el comptador de temps.
     */
    private void updateTimer() {
        long actualTime = System.currentTimeMillis();
        long tempsTorn = actualTime - iniciTorn;
        long tempsPartida = actualTime - iniciPartida;
        // Temps torn
        String hms = String.format("%02d:%02d:%02d", tempsTorn / 3600000,
                tempsTorn / 60000 - ((int) (tempsTorn / 3600000)) * 60,
                tempsTorn / 1000 - ((int) (tempsTorn / 60000)) * 60);
        labelTempsTorn.setText(hms);
        // Temps partida
        hms = String.format("%02d:%02d:%02d", tempsPartida / 3600000,
                tempsPartida / 60000 - ((int) (tempsPartida / 3600000)) * 60,
                tempsPartida / 1000 - ((int) (tempsPartida / 60000)) * 60);
        labelTempsPatida.setText(hms);
    }

    /**
     * Actualitza el panel lateral de dades.
     */
    private void updateData() {
        labelTorn.setText("Mou " + cp.getNomTorn() + (cp.getTorn() ? " (blanques)" : " (negres)"));
    }

    /**
     * Realitza accions en funció del resultat del moviment
     *
     * @param res Resultat de l'ultim moviment
     */
    private void processResult(int res) {
        switch (res) {
            case 0:
                labelEscac.setVisible(false);
                break;
            case 1:
                labelEscac.setVisible(true);
                break;
            case 2:
            case 3:
            case -1:
                vp.fiPartida(cp);
                break;
            default:
                throw new RuntimeException("Resultat desconegut: " + res);

        }
    }

    /**
     * Gestiona els clics del tauler
     *
     * @param x Posició X clicada.
     * @param y Posició Y clicada.
     */
    @Override
    public void clicPeca(int x, int y) {
        if (movimentIniciat) {
            int xp = posIni.first();
            int yp = posIni.second();
            int pos = movs.indexOf(new Pair<>(x, y));
            if (pos != -1) {
                int res = cp.mou(pos);
                setPos(xp, yp, cp.getPos(xp, yp));
                setPos(x, y, cp.getPos(x, y));
                iniciTorn = System.currentTimeMillis();
                processResult(res);
                updateData();
                if (res == 0 || res == 1) tornMaquina();
            }

            desmarcaPos(xp, yp);
            for (Pair<Integer, Integer> p : movs) {
                desmarcaPos(p.first(), p.second());
            }
            movimentIniciat = false;
        } else {
            char peca = getPos(x, y);
            if (peca == '-') return;
            if (Character.isUpperCase(peca) != cp.getTorn()) return;
            movs = cp.movimentsPossibles(x, y);
            for (Pair<Integer, Integer> p : movs) {
                marcaPos(p.first(), p.second());
            }
            if (movs.size() != 0) {
                movimentIniciat = true;
                posIni = new Pair<>(x, y);
                seleccionaPos(x, y);
            }
        }

    }

    void tornMaquina() {
        tMaquina = new Thread(() -> {

            boolean end = false;
            while (!end && cp.esTornMaquina()) {
                SwingUtilities.invokeLater(() -> setInteractable(false));
                final int res = cp.executaMoviment();
                if (res < 0 || res > 1) {
                    end = true;
                }
                if (interrupted()) return;
                SwingUtilities.invokeLater(() -> {
                    setInteractable(true);
                    reloadTauler();
                    iniciTorn = System.currentTimeMillis();
                    processResult(res);
                    updateData();
                    tMaquina = null;
                });
            }
        });
        tMaquina.start();
    }
}

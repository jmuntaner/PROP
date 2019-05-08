package views;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VistaJugar extends VistaAmbTauler {
    private long iniciTorn, iniciPartida;
    private JLabel labelTempsTorn, labelTempsPatida;

    VistaJugar(VistaPrincipal vp) {
        super(vp);
        iniciPartida = System.currentTimeMillis();
        iniciTorn = iniciPartida;


        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            long actualTime = System.currentTimeMillis();
            long tempsTorn = actualTime - iniciTorn;
            long tempsPartida = actualTime - iniciPartida;
            // Temps torn
            StringBuilder sb = new StringBuilder();
            if ((tempsTorn / 3600000) < 10) sb.append(0);
            sb.append((int) (tempsTorn / 3600000));
            tempsTorn %= 3600000;
            sb.append(':');
            if ((tempsTorn / 60000) < 10) sb.append(0);
            sb.append((int) (tempsTorn / 60000));
            tempsTorn %= 60000;
            sb.append(':');
            if ((tempsTorn / 1000) < 10) sb.append(0);
            sb.append((int) (tempsTorn / 1000));
            labelTempsTorn.setText(sb.toString());
            // Temps partida
            sb = new StringBuilder();
            if ((tempsPartida / 3600000) < 10) sb.append(0);
            sb.append((int) (tempsPartida / 3600000));
            tempsPartida %= 3600000;
            sb.append(':');
            if ((tempsPartida / 60000) < 10) sb.append(0);
            sb.append((int) (tempsPartida / 60000));
            tempsPartida %= 60000;
            sb.append(':');
            if ((tempsPartida / 1000) < 10) sb.append(0);
            sb.append((int) (tempsPartida / 1000));
            labelTempsPatida.setText(sb.toString());

        }, 1, 1, TimeUnit.SECONDS);

        initDisplays();
    }


    @Override
    JPanel getPanelDreta() {
        JPanel panelInfo = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        panelInfo.setLayout(gbl);
        GridBagConstraints gbc_info = new GridBagConstraints();
        gbc_info.gridx = 0;
        gbc_info.gridy = 0;
        gbc_info.gridwidth = 2;
        gbc_info.fill = GridBagConstraints.HORIZONTAL;
        labelTempsTorn = new JLabel("00:00:00");
        Font defaulltFont = labelTempsTorn.getFont();
        labelTempsTorn.setFont(new Font(defaulltFont.getName(), Font.PLAIN, 40));
        labelTempsTorn.setBorder(BorderFactory.createTitledBorder("Temps torn"));
        panelInfo.add(labelTempsTorn, gbc_info);

        gbc_info.gridwidth = 1;
        gbc_info.weightx = 1;
        gbc_info.gridy = 1;
        gbc_info.insets = new Insets(0, 4, 0, 0);
        panelInfo.add(new JLabel("Temps total:"), gbc_info);
        gbc_info.insets = new Insets(0, 0, 0, 4);
        gbc_info.fill = GridBagConstraints.NONE;
        gbc_info.gridx = 1;
        gbc_info.anchor = GridBagConstraints.LINE_END;
        labelTempsPatida = new JLabel("00:00:00");
        panelInfo.add(labelTempsPatida, gbc_info);


        gbc_info.gridy = 2;
        gbc_info.weighty = 1;
        gbc_info.fill = GridBagConstraints.BOTH;
        panelInfo.add(Box.createGlue(), gbc_info);

        // Posa un borde senzill al panel
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return panelInfo;
    }

    @Override
    public void clicPeca(int x, int y) {
        System.out.printf("Clic a %d, %d\n", x, y);
    }
}

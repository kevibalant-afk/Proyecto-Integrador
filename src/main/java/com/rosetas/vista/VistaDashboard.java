package com.rosetas.vista;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.rosetas.servicios.MovimientoService;

public class VistaDashboard extends JPanel {

    private MovimientoService service;

    private JPanel panelCards;
    private JPanel panelGraficas;

    public VistaDashboard(MovimientoService service) {
        this.service = service;

        setLayout(new BorderLayout());
        setBackground(new Color(236,240,241));

        initUI();
        cargarDatos();
    }

    private void initUI() {

        panelCards = new JPanel(new GridLayout(1,3,20,20));
        panelCards.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelCards.setBackground(new Color(236,240,241));

        panelGraficas = new JPanel(new GridLayout(1,2,20,20));
        panelGraficas.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelGraficas.setBackground(new Color(236,240,241));

        add(panelCards, BorderLayout.NORTH);
        add(panelGraficas, BorderLayout.CENTER);
    }

    public void cargarDatos() {

        panelCards.removeAll();
        panelGraficas.removeAll();

        double ingresos = service.obtenerTotalIngresos();
        double egresos = service.obtenerTotalEgresos();
        double ganancia = ingresos - egresos;

        panelCards.add(crearCard("Ingresos", ingresos, new Color(39,174,96)));
        panelCards.add(crearCard("Egresos", egresos, new Color(192,57,43)));
        panelCards.add(crearCard("Ganancia", ganancia,
                ganancia >= 0 ? new Color(39,174,96) : new Color(192,57,43)));

        panelGraficas.add(crearGraficaGanancias());
        panelGraficas.add(crearGraficaIngresosVsEgresos());

        revalidate();
        repaint();
    }

  
    private JPanel crearCard(String titulo, double valor, Color color) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblValor = new JLabel("$ " + String.format("%.2f", valor));
        lblValor.setForeground(Color.WHITE);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);

        return card;
    }


    private ChartPanel crearGraficaGanancias() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Double> datos = service.obtenerGananciaPorProducto();

        for (String producto : datos.keySet()) {
            dataset.addValue(datos.get(producto), "Ganancia", producto);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Ganancia por Producto",
                "Producto",
                "Valor",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int col) {
                double valor = dataset.getValue(row, col).doubleValue();
                return valor >= 0 ? new Color(39,174,96) : new Color(192,57,43);
            }
        };

        plot.setRenderer(renderer);

        return new ChartPanel(chart);
    }

   
    private ChartPanel crearGraficaIngresosVsEgresos() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(service.obtenerTotalIngresos(), "Ingresos", "Total");
        dataset.addValue(service.obtenerTotalEgresos(), "Egresos", "Total");

        JFreeChart chart = ChartFactory.createBarChart(
                "Ingresos vs Egresos",
                "Tipo",
                "Valor",
                dataset
        );

        return new ChartPanel(chart);
    }
    public void actualizarDashboard() {
       
        cargarDatos();
    }
}
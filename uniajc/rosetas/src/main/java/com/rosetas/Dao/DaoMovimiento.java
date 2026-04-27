package com.rosetas.Dao;

import com.rosetas.modelo.Movimiento;
import java.util.ArrayList;
public class DaoMovimiento {
private Movimiento movimiento;
private ArrayList<Movimiento> movimientos;

    public DaoMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
        this.movimientos = new ArrayList<>();
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
        System.out.println("Movimiento agregado: " + movimiento.getId_Movimiento());
    }

    public void guardarMovimiento() {
        // Aquí puedes implementar la lógica para guardar el movimiento en una base de datos o archivo
        System.out.println("Guardando movimiento: " + movimiento.getId_Movimiento());
    }
    public Movimiento getMovimiento() {
        return movimiento;
    }

}

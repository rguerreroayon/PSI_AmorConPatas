/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import objetosNegocio.Animal;
import objetosNegocio.EntradaMedica;

/**
 *
 * @author Rob Guerrero
 */
public class DAOAnimales implements DAOConexion {

    //TODOS
    @Override
    public ArrayList<Animal> transformarQuerySet() {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM Animal ORDER BY idAnimal ";
        ArrayList<Animal> animales = new ArrayList();

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idAnimal = Integer.valueOf(rs.getString("idAnimal"));
                int idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
                String especie = rs.getString("especie");
                GregorianCalendar fecha = Utilidades.convertirFecha_StringToGregorian(rs.getString("fechaRescate"));
                String raza = rs.getString("raza");
                String descripcionRescate = rs.getString("descripcionRescate");
                int esAdoptado = Integer.valueOf(rs.getString("esAdoptado"));
                String nombreAnimal = rs.getString("nombreAnimal");
                ArrayList<EntradaMedica> entradas = queryGetNotaMedicaPorIDAnimal(idAnimal);

                animales.add(
                        new Animal(nombreAnimal,
                                idAnimal,
                                especie,
                                raza,
                                fecha,
                                idVoluntario,
                                descripcionRescate,
                                entradas,
                                esAdoptado
                        )
                );

            }

            return animales;
        } catch (SQLException e) {
            System.out.println("Ouch, hubo un error obteniendo los animales");
        }

        return null;
    }

    public int queryAgregarAnimal(Animal animal) {
        Connection c = ConexionBD.getConection();
        String query = "INSERT INTO Animal(idVoluntario,especie,fechaRescate,raza,descripcionRescate,nombreAnimal) "
                + "VALUES (" + animal.getIDVoluntario() + ",'" + animal.getEspecie() + "','" + Utilidades.convertirFecha_GregorianToString(animal.getFecha())
                + "','" + animal.getRaza() + "','" + animal.getDescripcionRescate() + "','" + animal.getNombre() + "')";

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;

    }

    public int queryEliminarAnimal(int idAnimal) {
        Connection c = ConexionBD.getConection();
        String query = "DELETE FROM Animal WHERE idAnimal=" + String.valueOf(idAnimal);

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }

    public int queryEditarAnimal(Animal animal, int idAnimal) {
        Connection c = ConexionBD.getConection();
        String query = "UPDATE Animal "
                + "SET nombreAnimal='" + animal.getNombre() + "', descripcionRescate='" + animal.getDescripcionRescate() + "', raza='" + animal.getRaza() + "', fechaRescate='" 
                + Utilidades.convertirFecha_GregorianToString(animal.getFecha()) +"', especie='"+animal.getEspecie()+"', idVoluntario="+animal.getIDVoluntario()+" "
                + "WHERE idAnimal =" + idAnimal;

        try {

            PreparedStatement ps = c.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return -1;
    }

    public ArrayList<EntradaMedica> queryGetNotaMedicaPorIDAnimal(int idAnimal) {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM NotaMedica WHERE idAnimal =" + idAnimal;
        ArrayList<EntradaMedica> entradas = new ArrayList();

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                entradas.add(
                        new EntradaMedica(
                                Utilidades.convertirFecha_StringToGregorian(rs.getString("fechaNota")),
                                rs.getString("descripcionNota"),
                                Integer.valueOf(rs.getString("idAnimal")))
                );

            }

            return entradas;

        } catch (SQLException exception) {
            System.out.println("Oops, algo salio mal");
        }

        return null;

    }

    public ArrayList<Animal> queryGetAnimalesPorIDVoluntario(int idVoluntario) {
        ArrayList<Animal> animales = transformarQuerySet();
        ArrayList<Animal> porVol = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getIDVoluntario() == idVoluntario) {
                porVol.add(animale);
            }
        }

        return porVol;
    }

    public ArrayList<Animal> queryGetAnimalesPorNombreAnimal(String nombre) {
        ArrayList<Animal> rescatados = transformarQuerySet();
        ArrayList<Animal> rescatadosPorNombre = new ArrayList();

        for (Animal rescatado : rescatados) {
            if (rescatado.getNombre().contains(nombre)) {
                rescatadosPorNombre.add(rescatado);
            }
        }

        return rescatadosPorNombre;
    }

    public ArrayList<Animal> queryGetAnimalPorEspecie(String especie) {
        ArrayList<Animal> animales = transformarQuerySet();
        ArrayList<Animal> porEspecie = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getEspecie().equals(especie)) {
                porEspecie.add(animale);
            }
        }

        return porEspecie;
    }

    //Rescatados
    public ArrayList<Animal> queryGetAnimalesRescatados() {

        ArrayList<Animal> animales = transformarQuerySet();
        ArrayList<Animal> rescatados = new ArrayList();

        animales.stream().filter((animale) -> (animale.getIsAdoptado() == 0)).forEachOrdered((animale) -> {
            rescatados.add(animale);
        });

        return rescatados;

    }

    public ArrayList<Animal> queryGetAnimalesRescatadosPorNombreAnimal(String nombre) {
        ArrayList<Animal> rescatados = queryGetAnimalesRescatados();
        ArrayList<Animal> rescatadosPorNombre = new ArrayList();

        for (Animal rescatado : rescatados) {
            if (rescatado.getNombre().contains(nombre)) {
                rescatadosPorNombre.add(rescatado);
            }
        }

        return rescatadosPorNombre;
    }

    public Animal queryGetAnimalPorID(int id) {
        Connection c = ConexionBD.getConection();
        String query = "SELECT * FROM Animal WHERE idAnimal=" + id;
        Animal animal = null;

        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idAnimal = Integer.valueOf(rs.getString("idAnimal"));
                int idVoluntario = Integer.valueOf(rs.getString("idVoluntario"));
                String especie = rs.getString("especie");
                GregorianCalendar fecha = Utilidades.convertirFecha_StringToGregorian(rs.getString("fechaRescate"));
                String raza = rs.getString("raza");
                String descripcionRescate = rs.getString("descripcionRescate");
                int esAdoptado = Integer.valueOf(rs.getString("esAdoptado"));
                String nombreAnimal = rs.getString("nombreAnimal");
                ArrayList<EntradaMedica> entradas = queryGetNotaMedicaPorIDAnimal(idAnimal);

                animal = new Animal(nombreAnimal,
                        idAnimal,
                        especie,
                        raza,
                        fecha,
                        idVoluntario,
                        descripcionRescate,
                        entradas,
                        esAdoptado
                );

            }

            return animal;
        } catch (SQLException e) {
            System.out.println("Ouch, hubo un error obteniendo los animales");
        }

        return null;

    }

    public ArrayList<Animal> queryGetAnimalesRescatadosPorIDVoluntario(int idVoluntario) {
        ArrayList<Animal> animales = queryGetAnimalesRescatados();
        ArrayList<Animal> porVol = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getIDVoluntario() == idVoluntario) {
                porVol.add(animale);
            }
        }

        return porVol;
    }

    public ArrayList<Animal> queryGetAnimalRescatadosPorEspecie(String especie) {
        ArrayList<Animal> animales = queryGetAnimalesRescatados();
        ArrayList<Animal> porEspecie = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getEspecie().equals(especie)) {
                porEspecie.add(animale);
            }
        }

        return porEspecie;

    }

    //Adoptados
    public ArrayList<Animal> queryGetAnimalesAdoptados() {
        ArrayList<Animal> animales = transformarQuerySet();
        ArrayList<Animal> adoptados = new ArrayList();

        animales.stream().filter((animale) -> (animale.getIsAdoptado() == 1)).forEachOrdered((animale) -> {
            adoptados.add(animale);
        });

        return adoptados;
    }

    public ArrayList<Animal> queryGetAnimalAdoptadosPorNombre(String nombre) {
        ArrayList<Animal> adoptados = queryGetAnimalesAdoptados();
        ArrayList<Animal> adoptadosPorNombre = new ArrayList();

        adoptados.stream().filter((adoptado) -> (adoptado.getNombre().contains(nombre))).forEachOrdered((adoptado) -> {
            adoptadosPorNombre.add(adoptado);
        });

        return adoptadosPorNombre;
    }

    public ArrayList<Animal> queryGetAnimalesAdoptadosPorIDVoluntario(int idVoluntario) {
        ArrayList<Animal> animales = queryGetAnimalesAdoptados();
        ArrayList<Animal> porVol = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getIDVoluntario() == idVoluntario) {
                porVol.add(animale);
            }
        }

        return porVol;
    }

    public ArrayList<Animal> queryGetAnimalesAdoptadosPorEspecie(String especie) {
        ArrayList<Animal> animales = queryGetAnimalesAdoptados();
        ArrayList<Animal> porEspecie = new ArrayList();

        for (Animal animale : animales) {
            if (animale.getEspecie().equals(especie)) {
                porEspecie.add(animale);
            }
        }

        return porEspecie;
    }
}

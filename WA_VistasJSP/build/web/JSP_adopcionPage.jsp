<%--
    Document   : JSP_adopcionPage
    Created on : 21/03/2019, 12:24:04 AM
    Author     : Juan Enrique Solis Perla
--%>

<%@page import="objetosNegocio.Animal"%>
<%@page import="objetosNegocio.Adoptante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlets.*"%>
<%@page contentType="text/html"
        pageEncoding="UTF-8"%>
<%
    ServletAnimales servletAnimales = new ServletAnimales();
    ServletAdopciones servletAdopciones = new ServletAdopciones();
    ServletVoluntarios servletVoluntarios = new ServletVoluntarios();
    ServletAdoptantes servletAdoptantes = new ServletAdoptantes();

%>


<!DOCTYPE html>
<html lang="en" dir="ltr">
    <head>
        <title>Fundación Amor Con Patas</title>

        <!-- Semantic UI and JQuery -->
        <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            crossorigin="anonymous"
        ></script>

        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css"
            />

        <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>

        <style>
            .vertical-menu {
                width: 212px; /* Set a width if you like */
            }

            .vertical-menu a {
                background-color: #eee; /* Grey background color */
                color: black; /* Black text color */
                display: block; /* Make the links appear below each other */
                padding: 12px; /* Add some padding */
                text-decoration: none; /* Remove underline from links */
            }

            .vertical-menu a:hover {
                background-color: #ccc; /* Dark grey background on mouse-over */
            }

            .vertical-menu a.active {
                background-color: #484d48; /* Add a green color to the "active/current" link */
                color: white;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="ui inverted segment">
                <div class="ui inverted secondary menu">
                    <a class="active item">Home</a>

                    <a class="item">Adopciones</a>

                    <a class="item">Voluntariado</a>

                    <a class="item">Consultas</a>
                </div>
            </div>

            <br />
            <br />
            <br />

            <div class="ui container">
                <div class="ui grid">
                    <div
                        class="four wide center aligned  column"
                        style="background-color:#e5e5e5;e"
                        >
                        <div class="ui container">
                            <div class="ui card">
                                <div class="image">
                                    <img
                                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRn3qmREi6Bag9BxxTvSCdXKqL8G2mBNjSFMi1nBZ1r92jNniSwnw"
                                        />
                                </div>

                                <div class="content">
                                    <a class="header">Roberto</a>

                                    <div class="meta">
                                        <span class="date">Joined in 2019</span>
                                    </div>

                                    <div class="description">
                                        Roberto is a Software Engineer living in Sonora, Mexico.
                                    </div>
                                </div>

                                <div class="extra content">
                                    <a>
                                        <i class="user icon"></i>
                                        22 Friends
                                    </a>
                                </div>
                            </div>

                            <div class="vertical-menu">
                                <a href="#" class="active">Mi Cuenta</a>

                                <a href="#">Tomar Asistencia</a>

                                <a href="#">Registrar Rescate</a>

                                <a href="#">Registrar Adopcion</a>

                                <a href="#">Registrar Adoptante</a>

                                <a href="#">Generar Reporte</a>
                            </div>
                        </div>
                    </div>

                    <div class="twelve wide column" style="background-color: lightgray;">
                        <div class="ui container">


                            <form action="ServletAnimales"> 
                                <p>Nombre nuevo del animal: </p><input type="text" id="campoNombreAnimalNuevo" name="campoNombreAnimalNuevo"> 
                                <p>Nombre de animal a buscar: </p><input type="text" id="campoNombreAnimalBuscar" name="campoNombreAnimalBuscar"> 
                                <input type="submit" id="btnBuscarAnimal" name="btnBuscarAnimal">
                                <p>Seleccionar animal: </p>
                                <select name="animalSelect">
                                    <%                                        try {
                                            ArrayList<Animal> animales = servletAnimales.obtenerAnimalesRescatadosPorNombre();

                                            for (Animal animal : animales) {
                                                out.println("<option name='idAnimal' value='" + String.valueOf(animal.getIdAnimal()) + "'>" + animal.getIdAnimal() + " - " + animal.getNombre() + "</option>");
                                            }

                                        } catch (Exception e) {
                                            out.println("U DID SOMETHING NASTY!");
                                        }


                                    %>                        
                                </select>
                            </form>


                            <form action="ServletAdoptantes"> 
                                <p>Nombre del adoptante </p><input type="text" id="campoNombreAdoptante" name="campoNombreAdoptante"> 
                                <input type="submit" id="btnBuscarAdoptante" name="btnBuscarAdoptante">
                                <p>Seleccionar adoptante: </p>
                                <select name="adoptanteSelect">
                                    <%                                        try {
                                            ArrayList<Adoptante> adoptantes = servletAdoptantes.obtenerAdoptantesNombre();

                                            for (Adoptante adoptante : adoptantes) {
                                                out.println("<option name='idAdoptante' value='" + String.valueOf(adoptante.getIdAdoptante()) + "'>" + adoptante.getIdAdoptante() + " - " + adoptante.getNombre() + "</option>");
                                            }

                                        } catch (Exception e) {
                                            out.println("U DID SOMETHING NASTY!");
                                        }


                                    %>                        
                                </select>
                            </form>




                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

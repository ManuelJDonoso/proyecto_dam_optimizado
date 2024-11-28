/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.modelos.examen;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Base_datos {

//------------------------------------------------Conexiones-------------------------------------------------------------
    /**
     * Establece una conexión con una base de datos SQLite.
     * <p>
     * Este método crea una conexión a la base de datos ubicada en
     * "src/main/resources/db/db.db". Si la conexión se establece correctamente,
     * se devuelve un objeto {@link Connection}. En caso de que ocurra algún
     * error, se imprime el mensaje de error y se devuelve null.
     * </p>
     *
     * @return un objeto {@link Connection} si la conexión es exitosa; null si
     * ocurre una excepción.
     */
    public static Connection conectarSqlite() {

        String DB_URL = "jdbc:sqlite:src/main/resources/db/db.db";

        try {
            Class.forName("org.sqlite.JDBC");

            Connection conexion = DriverManager.getConnection(DB_URL);
            return conexion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Verifica si la conexión a la base de datos es válida (no es nula).
     *
     * Este método se utiliza para comprobar si una conexión a la base de datos
     * es válida antes de intentar usarla en operaciones posteriores. Retorna
     * `true` si la conexión no es nula, lo que indica que está activa y lista
     * para su uso; de lo contrario, devuelve `false`.
     *
     * @param conn La conexión a la base de datos que se desea verificar.
     * @return `true` si la conexión no es nula, `false` si la conexión es nula.
     */
    public static boolean isConnection(Connection conn) {
        return conn != null;
    }

    /**
     * Establece una conexión con una base de datos MySQL utilizando JDBC.
     *
     * Este método se encarga de crear una conexión a una base de datos MySQL
     * especificando la URL de la base de datos, el nombre de usuario y la
     * contraseña. Si la conexión es exitosa, se devuelve un objeto
     * {@link Connection}, de lo contrario, se maneja la excepción y se imprime
     * un mensaje de error.
     *
     * @param URL La URL de la base de datos, que incluye el nombre del host, el
     * puerto y el nombre de la base de datos (por ejemplo,
     * jdbc:mysql://localhost:3306/mi_base).
     * @param USER El nombre de usuario para la conexión a la base de datos.
     * @param PASSWORD La contraseña asociada al usuario de la base de datos.
     * @return Una conexión {@link Connection} a la base de datos, o
     * {@code null} si no se pudo establecer la conexión.
     */
    public static Connection connectMysql(String URL, String USER, String PASSWORD) {

        Connection conn = null;
        try {
            // Cargar el driver de MySQL (esto es opcional en versiones recientes de JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conn = DriverManager.getConnection(URL + "?connectTimeout=2000", USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
            // Crear una declaración para cambiar el wait_timeout a 3 segundos

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Establece una conexión con la base de datos MySQL local.
     *
     * Este método simplifica la conexión a una base de datos MySQL local
     * utilizando las credenciales predeterminadas (usuario "root" y contraseña
     * vacía). Utiliza el método {@link #connectMysql(String, String, String)}
     * para establecer la conexión con la base de datos ubicada en "localhost"
     * en el puerto 3306, y la base de datos "proyecto_dam".
     *
     * @return Una conexión {@link Connection} a la base de datos "proyecto_dam"
     * en el servidor local, o {@code null} si no se pudo establecer la
     * conexión.
     */
    public static Connection connectMysqlLocal() {
        return connectMysql("jdbc:mysql://localhost:3306/proyecto_dam", "root", "");

    }

    /**
     * Establece una conexión con la base de datos MySQL en una red remota.
     *
     * Este método simplifica la conexión a una base de datos MySQL que se
     * encuentra en un servidor remoto, utilizando el método
     * {@link #connectMysql(String, String, String)}. La base de datos se
     * encuentra en la dirección IP "192.168.1.100", en el puerto 3306, y se
     * conecta a la base de datos "proyecto_dam" utilizando el usuario "manuel"
     * y una contraseña "manuel".
     *
     * @return Una conexión {@link Connection} a la base de datos "proyecto_dam"
     * en el servidor remoto, o {@code null} si no se pudo establecer la
     * conexión.
     */
    public static Connection connectMysqlRed() {
        return connectMysql("jdbc:mysql://192.168.1.100:3306/proyecto_dam", "manuel", "manuel");

    }

    /**
     * Establece una conexión con la base de datos MySQL en un servidor remoto a
     * través de un dominio.
     *
     * Este método simplifica la conexión a una base de datos MySQL que se
     * encuentra en un servidor remoto accesible a través del dominio
     * "manueldonoso.duckdns.org", en el puerto 3306. Utiliza el método
     * {@link #connectMysql(String, String, String)} para establecer la conexión
     * con la base de datos "proyecto_dam" utilizando el usuario "manuel" y la
     * contraseña "manuel".
     *
     * @return Una conexión {@link Connection} a la base de datos "proyecto_dam"
     * en el servidor remoto, o {@code null} si no se pudo establecer la
     * conexión.
     */
    public static Connection connectMysqlRemota() {
        return connectMysql("jdbc:mysql://manueldonoso.duckdns.org:3306/proyecto_dam", "manuel", "manuel");

    }

    //--------------------------------------------------contrato------------------------------------------------------------------------------
    /**
     * Registra un nuevo producto en las tablas "Registro_tabla" y
     * "usuario_tabla" utilizando los datos de un objeto `Usuario`. Inserta un
     * nuevo registro en la tabla "Registro_tabla" con un valor fijo de `id`
     * igual a `1`, y en la tabla "usuario_tabla" con los valores de `Usuario`,
     * `Nombre`, `Apellidos`, y `Email`. La operación se realiza dentro de una
     * transacción para garantizar la consistencia de ambas inserciones.
     *
     * <p>
     * Ejemplo de uso:</p>
     * <pre>{@code
     * Usuario user = new Usuario("usuario123", "Manuel", "Donoso", "Manuel.Donoso@example.com");
     * RegistrarProductoContrato(user);
     * }</pre>
     *
     * @param user El objeto `Usuario` que contiene los datos a insertar en las
     * tablas. El parámetro `Usuario` debe tener los métodos `getUsuario()`,
     * `getNombre()`, `getApellidos()` y `getEmail()` para obtener los valores
     * que se insertarán en la base de datos.
     *
     */
    public static void RegistrarProductoContrato(Usuario user) {
        String usuario = user.getUsuario(), nombre = user.getNombre(), apellidos = user.getApellidos(), email = user.getEmail();
        // Definir las consultas SQL para insertar los registros en ambas tablas
        String sqlRegistro = "INSERT INTO Registro_tabla (id, Nombre, Apellidos, Email) VALUES (1, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO usuario_tabla (Usuario, Nombre, Apellidos, Email,fk_tipo) VALUES (?, ?, ?, ?,'ADMINISTRADOR')";

        // Conexión y ejecución de las consultas dentro de una transacción
        try (Connection conn = conectarSqlite()) {
            // Iniciar una transacción
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtRegistro = conn.prepareStatement(sqlRegistro); PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {

                // Establecer los parámetros para la tabla Registro_tabla
                pstmtRegistro.setString(1, nombre);
                pstmtRegistro.setString(2, apellidos);
                pstmtRegistro.setString(3, email);

                // Ejecutar la inserción en Registro_tabla
                pstmtRegistro.executeUpdate();

                // Establecer los parámetros para la tabla usuario_tabla
                pstmtUsuario.setString(1, usuario);  // Usuario como parámetro
                pstmtUsuario.setString(2, nombre);
                pstmtUsuario.setString(3, apellidos);
                pstmtUsuario.setString(4, email);

                // Ejecutar la inserción en usuario_tabla
                pstmtUsuario.executeUpdate();

                // Confirmar la transacción
                conn.commit();
                System.out.println("Registros insertados exitosamente en Registro_tabla y usuario_tabla.");

            } catch (SQLException e) {
                // En caso de error, revertir la transacción
                conn.rollback();
                System.out.println("Error al insertar los registros: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

    }

    /**
     * Obtiene el nombre completo de un usuario desde la base de datos,
     * concatenando los campos "nombre" y "Apellidos" de la tabla
     * "Registro_tabla".
     *
     * @return el nombre completo del usuario como un String (formato: "nombre
     * Apellidos"), o {@code null} si no se encuentra ningún registro o ocurre
     * un error en la conexión.
     */
    public static String nombreUsuarioContrato() {
        String sql = "SELECT nombre || ' ' || Apellidos AS nombre_completo FROM Registro_tabla;";
        String nombreCompleto = null;

        try (Connection conn = conectarSqlite(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Si hay un resultado, obtener el nombre completo
            if (rs.next()) {
                nombreCompleto = rs.getString("nombre_completo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCompleto;
    }

    /**
     * Obtiene una fecha formateada de la tabla `Registro_tabla` en el formato
     * "día de la semana día mes año" (ejemplo: "martes 7 diciembre 2024"). La
     * función utiliza `strftime` para extraer los componentes de la fecha y
     * concatenarlos en el formato requerido.
     *
     * @return Una cadena que representa la fecha en el formato especificado, o
     * `null` si no hay registros en la tabla.
     */
    public static String fechaContrato() {
        String sql = "SELECT strftime('%w', FechaContrato) AS dia_semana, "
                + "strftime('%d', FechaContrato) AS dia_mes, "
                + "strftime('%m', FechaContrato) AS mes, "
                + "strftime('%Y', FechaContrato) AS anio "
                + "FROM Registro_tabla;";

        String Fecha = null;

        try (Connection conn = conectarSqlite(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Si hay un resultado, obtener la fecha formateada
            if (rs.next()) {
                String diaSemana = obtenerDiaSemana(rs.getString("dia_semana"));  // Convertir el número del día en su nombre en español
                String mes = obtenerNombreMes(rs.getString("mes"));               // Convertir el número del mes en su nombre en español
                Fecha = diaSemana + " " + rs.getString("dia_mes") + " " + mes + " " + rs.getString("anio");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Fecha;
    }
// Convierte el número de día en el nombre del día en español

    private static String obtenerDiaSemana(String diaSemana) {
        switch (diaSemana) {
            case "0":
                return "domingo";
            case "1":
                return "lunes";
            case "2":
                return "martes";
            case "3":
                return "miércoles";
            case "4":
                return "jueves";
            case "5":
                return "viernes";
            case "6":
                return "sábado";
            default:
                return "";
        }
    }

// Convierte el número de mes en el nombre del mes en español
    private static String obtenerNombreMes(String mes) {
        switch (mes) {
            case "01":
                return "enero";
            case "02":
                return "febrero";
            case "03":
                return "marzo";
            case "04":
                return "abril";
            case "05":
                return "mayo";
            case "06":
                return "junio";
            case "07":
                return "julio";
            case "08":
                return "agosto";
            case "09":
                return "septiembre";
            case "10":
                return "octubre";
            case "11":
                return "noviembre";
            case "12":
                return "diciembre";
            default:
                return "";
        }
    }

//---------------------------------------usuarios----------------------------------------------------------------------------------------
    /**
     * Actualiza el usuario y la contraseña almacenados en la tabla
     * Usuario_Guardado_tabla. Este método sobrescribe los valores de las
     * columnas `Usuario` y `Pass` para el registro con `id = 1`.
     *
     * @param Usuario el nombre de usuario que se desea almacenar.
     * @param Pass la contraseña asociada al usuario.
     */
    public static void MemorizarUsuario(String Usuario, String Pass) {
        String sql = "UPDATE Usuario_Guardado_tabla SET Usuario = ?, Pass = ? WHERE id = 1";
        try (PreparedStatement pstmtRegistro = conectarSqlite().prepareStatement(sql)) {
            // Establecer los parámetros para la tabla Usuario_Guardado_tabla
            pstmtRegistro.setString(1, Usuario);
            pstmtRegistro.setString(2, Pass);
            // Ejecutar la actualización en Usuario_Guardado_tabla
            pstmtRegistro.executeUpdate();
        } catch (SQLException e) {
            // Manejo de errores omitido
        }
    }

    /**
     * Recupera el nombre de usuario almacenado en la tabla
     * Usuario_Guardado_tabla para el registro con id = 1.
     *
     * @return el nombre de usuario almacenado, o null si no se encuentra o
     * ocurre un error.
     */
    public static String UsuarioMemorizado() {
        String sql = "SELECT Usuario FROM Usuario_Guardado_tabla WHERE id = 1";
        String usuario = null;

        try (Connection conn = conectarSqlite(); // Asumiendo que conectarSqlite() devuelve una conexión válida
                 PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Si existe un registro con id = 1, se obtiene el valor del campo 'Usuario'
            if (rs.next()) {
                usuario = rs.getString("Usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar el usuario memorizado: " + e.getMessage());
        }

        return usuario;
    }

    /**
     * Recupera el nombre de usuario almacenado en la tabla
     * Usuario_Guardado_tabla para el registro con id = 1.
     *
     * @return el pass de usuario almacenado, o null si no se encuentra o ocurre
     * un error.
     */
    public static String PassMemorizado() {
        String sql = "SELECT Pass FROM Usuario_Guardado_tabla WHERE id = 1";
        String Pass = null;

        try (Connection conn = conectarSqlite(); // Asumiendo que conectarSqlite() devuelve una conexión válida
                 PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Si existe un registro con id = 1, se obtiene el valor del campo 'Usuario'
            if (rs.next()) {
                Pass = rs.getString("Pass");
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar el Pass memorizado: " + e.getMessage());
        }

        return Pass;
    }

    /**
     * Inserta quince registros de usuarios en la tabla `usuario_tabla`. Este
     * método establece una conexión a la base de datos y usa
     * `PreparedStatement` para insertar cinco filas de usuarios de tipo
     * `ADMINISTRADOR`, cinco de tipo `PROFESOR` y cinco de tipo `ALUMNO`.
     *
     * Cada usuario tendrá valores específicos para los campos `Usuario`,
     * `Nombre`, `Apellidos`, `Pass`, `Email`, `fk_tipo`, y la fecha de alta
     * (`fechaAlta`).
     *
     * @param conn La conexión a la base de datos.
     * @throws java.sql.SQLException
     */
    public static void insertarUsuarios(Connection conn) throws SQLException {
        String sql = "INSERT INTO usuario_tabla (Usuario, Nombre, Apellidos, Direccion, Telefono, Pass, Email, fk_tipo, fechaAlta, fechaBaja) "
                + "VALUES (?, ?, ?, '', '', ?, ?, ?, ?, NULL)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Formato de la fecha de alta
            String fechaAlta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Datos de los usuarios organizados por tipo
            String[][] usuarios = {
                {"administrador1", "Rocio", "Perez", "cambiame", "dev1@sdes.es", "ADMINISTRADOR"},
                {"administrador2", "Carlos", "Lopez", "cambiame", "dev2@sdes.es", "ADMINISTRADOR"},
                {"administrador3", "Maria", "Fernandez", "cambiame", "dev3@sdes.es", "ADMINISTRADOR"},
                {"administrador4", "Luis", "Gomez", "cambiame", "dev4@sdes.es", "ADMINISTRADOR"},
                {"administrador5", "Ana", "Martinez", "cambiame", "dev5@sdes.es", "ADMINISTRADOR"},
                {"profesor1", "Jose", "Rodriguez", "cambiame", "prof1@sdes.es", "PROFESOR"},
                {"profesor2", "Laura", "Diaz", "cambiame", "prof2@sdes.es", "PROFESOR"},
                {"profesor3", "Miguel", "Santos", "cambiame", "prof3@sdes.es", "PROFESOR"},
                {"profesor4", "Lucia", "Ramirez", "cambiame", "prof4@sdes.es", "PROFESOR"},
                {"profesor5", "Sofia", "Ortega", "cambiame", "prof5@sdes.es", "PROFESOR"},
                {"alumno1", "Pedro", "Suarez", "cambiame", "alumno1@sdes.es", "ALUMNO"},
                {"alumno2", "Raquel", "Martinez", "cambiame", "alumno2@sdes.es", "ALUMNO"},
                {"alumno3", "Julio", "Gonzalez", "cambiame", "alumno3@sdes.es", "ALUMNO"},
                {"alumno4", "Andrea", "Lozano", "cambiame", "alumno4@sdes.es", "ALUMNO"},
                {"alumno5", "Isabel", "Reyes", "cambiame", "alumno5@sdes.es", "ALUMNO"}
            };

            // Insertar cada usuario
            for (String[] usuario : usuarios) {
                pstmt.setString(1, usuario[0]); // Usuario
                pstmt.setString(2, usuario[1]); // Nombre
                pstmt.setString(3, usuario[2]); // Apellidos
                pstmt.setString(4, usuario[3]); // Pass
                pstmt.setString(5, usuario[4]); // Email
                pstmt.setString(6, usuario[5]); // fk_tipo
                pstmt.setString(7, fechaAlta);  // fechaAlta
                pstmt.executeUpdate();
            }

            System.out.println("Registros insertados exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar los registros: " + e.getMessage());
            throw e; // Propagar la excepción
        }
    }

    /**
     * Verifica si un usuario con el nombre de usuario y contraseña
     * proporcionados existe en la base de datos. Si el usuario existe, devuelve
     * el valor de `fk_tipo` correspondiente; si no, devuelve `null`.
     *
     * @param conn La conexión a la base de datos.
     * @param usuario El nombre de usuario a verificar.
     * @param pass La contraseña del usuario.
     * @return El valor de `fk_tipo` si el usuario existe y la contraseña es
     * correcta, o `null` si no coincide.
     * @throws java.sql.SQLException
     */
    public static String verificarUsuario(Connection conn, String usuario, String pass) throws SQLException {
        String sql = "SELECT fk_tipo FROM usuario_tabla WHERE Usuario = ? AND Pass = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario); // Establecer el nombre de usuario en el primer parámetro
            pstmt.setString(2, pass);    // Establecer la contraseña en el segundo parámetro
            String tipo = "";
            // Ejecutar la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retornar fk_tipo si el usuario y la contraseña son correctos
                    tipo = rs.getString("fk_tipo");

                    return tipo;
                } else {
                    // Retornar null si el usuario no existe o la contraseña es incorrecta
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario: " + e.getMessage());
            throw e; // Propagar la excepción
        }
    }

    /**
     * Elimina todos los registros de la tabla `usuario_tabla` en la base de
     * datos. Este método no requiere ningún parámetro adicional, ya que elimina
     * todos los registros existentes en la tabla.
     *
     * @param conn La conexión a la base de datos.
     * @return `true` si se eliminaron registros con éxito, `false` si no se
     * afectaron registros o si ocurrió un error.
     * @throws java.sql.SQLException
     */
    public static boolean eliminarTodosUsuarios(Connection conn) throws SQLException {
        String sql = "DELETE FROM usuario_tabla";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Ejecutar la eliminación y verificar si se eliminó alguna fila
            int filasAfectadas = pstmt.executeUpdate();
            boolean borrada = filasAfectadas > 0;
            System.out.println("Los datos de usuario se borraron correctamente");
            return borrada;
        } catch (SQLException e) {
            System.out.println("Error al eliminar todos los usuarios: " + e.getMessage());
            throw e; // Propagar la excepción
        }
    }

    /**
     * Cambia la contraseña de un usuario en la tabla usuario_tabla.
     *
     * Este método actualiza la contraseña de un usuario en la base de datos,
     * guardándola en texto plano. Es importante recordar que almacenar
     * contraseñas en texto plano no es seguro y solo debe usarse en entornos de
     * desarrollo o cuando no se requiera seguridad.
     *
     * @param conn La conexión a la base de datos.
     * @param usuario El nombre del usuario cuyo contraseña será cambiada.
     * @param pass La nueva contraseña en texto plano.
     */
    public static void CambioContraseña(Connection conn, String usuario, String pass) {
        // Consulta SQL para actualizar la contraseña
        String sql = "UPDATE usuario_tabla SET Pass = ? WHERE Usuario = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Configura los parámetros de la consulta
            pstmt.setString(1, pass);      // Establece la nueva contraseña en texto plano
            pstmt.setString(2, usuario);   // Establece el usuario

            // Ejecuta la actualización
            int filasActualizadas = pstmt.executeUpdate();

            // Verifica si la actualización fue exitosa
            if (filasActualizadas > 0) {
                System.out.println("Contraseña actualizada correctamente para el usuario: " + usuario);
            } else {
                System.out.println("No se encontró el usuario: " + usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Base_datos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Usuario BuscarUsuario_Usuario(Connection conn, String usuario) throws SQLException {
        String sql = "SELECT * FROM usuario_tabla WHERE Usuario = ?";
        String sqlAsignaturas = "SELECT * FROM usuario_asignatura WHERE usuario =?";
        List<String> asignaturasExistentes = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql); PreparedStatement pstmtAsignaturas = conn.prepareStatement(sqlAsignaturas)) {
            pstmt.setString(1, usuario); // Establecer el nombre de usuario en el primer parámetro

            // Ejecutar la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Retornar fk_tipo si el usuario y la contraseña son correctos
                    Usuario user = new Usuario();
                    user.setNombre(rs.getString("Nombre"));
                    user.setApellidos(rs.getString("Apellidos"));
                    user.setDireccion(rs.getString("Direccion"));
                    user.setTelefono(rs.getString("Telefono"));
                    user.setFoto(rs.getBytes("Foto"));
                    user.setEmail(rs.getString("Email"));
                    user.setUsuario(rs.getString("Usuario"));

                    switch (rs.getString("fk_tipo")) {
                        case "ALUMNO":
                            user.setTipo(Usuario.TipoUsuario.ALUMNO);
                            break;
                        case "ADMINISTRADOR":
                            user.setTipo(Usuario.TipoUsuario.ADMINISTRADOR);
                            break;
                        case "PROFESOR":
                            user.setTipo(Usuario.TipoUsuario.PROFESOR);
                            break;
                        default:

                    }

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    if (rs.getString("fechaAlta") != null && !rs.getString("fechaAlta").isBlank()) {
                        System.out.println(rs.getString("fechaAlta"));
                        try {

                            java.util.Date fechaAlta = formatter.parse(rs.getString("fechaAlta"));

                            user.setFechaAlta(Acciones.utilDate_to_sqlDate(fechaAlta));
                        } catch (ParseException ex) {
                            long timeStamp = Long.parseLong(rs.getString("fechaAlta"));
                            user.setFechaAlta(new Date(timeStamp));
                        }
                    }

                    if (rs.getString("fechaBaja") != null && !rs.getString("fechaBaja").isBlank()) {
                        try {
                            java.util.Date fechaBaja = formatter.parse(rs.getString("fechaBaja"));

                            user.setFechaBaja(Acciones.utilDate_to_sqlDate(fechaBaja));
                        } catch (ParseException ex) {
                            long timeStamp = Long.parseLong(rs.getString("fechaBaja"));
                            user.setFechaAlta(new Date(timeStamp));
                        }

                    }

                    pstmtAsignaturas.setString(1, user.getUsuario());
                    try (ResultSet rs2 = pstmtAsignaturas.executeQuery()) {
                        while (rs2.next()) {
                            asignaturasExistentes.add(rs2.getString("asignatura"));

                        }
                    }

                    user.setAsignaturas(asignaturasExistentes);
                    System.out.println(user.toString());
                    return user;
                } else {
                    // Retornar null si el usuario no existe o la contraseña es incorrecta
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario: " + e.getMessage());
            throw e; // Propagar la excepción
        }
    }

    public static boolean ModificarUsuario(Connection conn, Usuario user) {
        String sql = "UPDATE usuario_tabla "
                + "SET Nombre = ?, Apellidos = ?, Direccion = ?, Telefono = ?, Foto = ?, "
                + "Email = ?, fk_tipo = ?, fechaAlta = ?, fechaBaja = ? "
                + "WHERE Usuario = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asignar valores a los parámetros de la consulta
            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getApellidos());
            pstmt.setString(3, user.getDireccion());
            pstmt.setString(4, user.getTelefono());
            pstmt.setBytes(5, user.getFoto());
            pstmt.setString(6, user.getEmail());
            // Manejar el tipo de usuario, convirtiendo a String o asignando NULL si no está definido
            if (user.getTipo() != null) {
                pstmt.setString(7, user.getTipo().toString());
            } else {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            }
            pstmt.setDate(8, user.getFechaAlta());
            pstmt.setDate(9, user.getFechaBaja());
            pstmt.setString(10, user.getUsuario());

            // Ejecutar la consulta
            int filasActualizadas = pstmt.executeUpdate();

            // Retornar true si se actualizó al menos un registro
            return filasActualizadas > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean IsUsuario(Connection conn, String usuario) {
        String sql = "SELECT fk_tipo FROM usuario_tabla WHERE Usuario = ? ";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario); // Establecer el nombre de usuario en el primer parámetro

            String tipo = "";
            // Ejecutar la consulta
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    return true;

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Base_datos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void GuardarUsuarioNuevo(Connection conn, Usuario user) throws SQLException {
        String sql = "INSERT INTO usuario_tabla (Usuario, Nombre, Apellidos, Direccion, Telefono, Pass, Email, fk_tipo, fechaAlta, fechaBaja) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL)";
        String sqlRegistroAsignaturas = "INSERT INTO usuario_asignatura (usuario, asignatura) VALUES (?, ?)";

        try (PreparedStatement pstmtUsuario = conn.prepareStatement(sql); PreparedStatement pstmtAsignaturas = conn.prepareStatement(sqlRegistroAsignaturas)) {

            // Formato de la fecha de alta
            String fechaAlta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Insertar datos del usuario
            pstmtUsuario.setString(1, user.getUsuario()); // Usuario
            pstmtUsuario.setString(2, user.getNombre()); // Nombre
            pstmtUsuario.setString(3, user.getApellidos()); // Apellidos
            pstmtUsuario.setString(4, user.getDireccion()); // Direccion
            pstmtUsuario.setString(5, user.getTelefono()); // Telefono
            pstmtUsuario.setString(6, user.getPass()); // Pass
            pstmtUsuario.setString(7, user.getEmail()); // Email

            // Manejar el tipo de usuario, asignando NULL si no está definido
            if (user.getTipo() != null) {
                pstmtUsuario.setString(8, user.getTipo().toString());
            } else {
                pstmtUsuario.setNull(8, java.sql.Types.VARCHAR);
            }

            pstmtUsuario.setString(9, fechaAlta); // fechaAlta
            pstmtUsuario.executeUpdate();

            System.out.println("Usuario registrado exitosamente.");

            // Insertar asignaturas asociadas al usuario
            List<String> asignaturas = user.getAsignaturas();
            if (asignaturas != null) {
                for (String asignatura : asignaturas) {
                    pstmtAsignaturas.setString(1, user.getUsuario()); // Usuario
                    pstmtAsignaturas.setString(2, asignatura); // Asignatura
                    pstmtAsignaturas.executeUpdate();
                }
                System.out.println("Asignaturas asociadas exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar los registros: " + e.getMessage());
            throw e; // Propagar la excepción
        }
    }
    //----------------------------------------------------Asignaturas---------------------------------------------------------

    public static void AltaAsignatura(Connection conn, String Asignatura) {
        // Consulta para verificar si la asignatura ya existe
        String checkSql = "SELECT COUNT(*) FROM Asignaturas_tabla WHERE asignatura = ?";

        // Consulta para insertar una nueva asignatura
        String insertSql = "INSERT INTO Asignaturas_tabla (asignatura) VALUES (?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            // Configuramos el parámetro para verificar
            checkStmt.setString(1, Asignatura);

            // Ejecutamos la consulta de verificación
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("La asignatura '" + Asignatura + "' ya existe en la base de datos.");
                return; // Salimos del método si ya existe
            }

            // Si no existe, la insertamos
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, Asignatura);
                insertStmt.executeUpdate();
                System.out.println("Asignatura '" + Asignatura + "' dada de alta exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar o insertar la asignatura: " + e.getMessage());
        }
    }

    public static List<String> obtenerAsignaturasActivas(Connection conn) {
        List<String> asignaturasActivas = new ArrayList<>();
        String sql = "SELECT asignatura FROM Asignaturas_tabla WHERE activo = 1";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Recorremos el ResultSet para obtener las asignaturas activas
            while (rs.next()) {
                asignaturasActivas.add(rs.getString("asignatura"));
            }

            // Imprimimos las asignaturas activas para verificar
            if (asignaturasActivas.isEmpty()) {
                System.out.println("No hay asignaturas activas.");
            } else {
                System.out.println("Asignaturas activas: " + asignaturasActivas);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las asignaturas activas: " + e.getMessage());
        }
        Collections.sort(asignaturasActivas);
        return asignaturasActivas;
    }

    public static List<String> obtenerAsignaturasInActivas(Connection conn) {
        List<String> asignaturasinActivas = new ArrayList<>();
        String sql = "SELECT asignatura FROM Asignaturas_tabla WHERE activo = 0";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Recorremos el ResultSet para obtener las asignaturas activas
            while (rs.next()) {
                asignaturasinActivas.add(rs.getString("asignatura"));
            }

            // Imprimimos las asignaturas activas para verificar
            if (asignaturasinActivas.isEmpty()) {
                System.out.println("No hay asignaturas activas.");
            } else {
                System.out.println("Asignaturas activas: " + asignaturasinActivas);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las asignaturas activas: " + e.getMessage());
        }
        Collections.sort(asignaturasinActivas);
        return asignaturasinActivas;
    }

    public static void desactivarAsignatura(Connection conn, String asignatura) {
        String sql = "UPDATE Asignaturas_tabla SET activo = 0 WHERE asignatura= ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer el valor para el parámetro "asignatura"
            pstmt.setString(1, asignatura);

            // Ejecutar la actualización
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Asignatura '" + asignatura + "' desactivada correctamente.");
            } else {
                System.out.println("No se encontró la asignatura '" + asignatura + "' para desactivarla.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desactivar la asignatura: " + e.getMessage());
        }
    }

    public static void activarAsignatura(Connection conn, String asignatura) {
        String sql = "UPDATE Asignaturas_tabla SET activo = 1 WHERE asignatura= ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Establecer el valor para el parámetro "asignatura"
            pstmt.setString(1, asignatura);

            // Ejecutar la actualización
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Asignatura '" + asignatura + "' desactivada correctamente.");
            } else {
                System.out.println("No se encontró la asignatura '" + asignatura + "' para desactivarla.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desactivar la asignatura: " + e.getMessage());
        }
    }

    public static ObservableList<Usuario> buscarUser(Connection conn, String fk_ClaseUser, String Nombre, String Apellidos,
            String Telefono, String Email, String Usuario, String Direccion) {

        String sql = "SELECT Nombre, Apellidos, Telefono, Email, Usuario, Direccion, fk_tipo "
                + "FROM usuario_tabla "
                + "WHERE (fk_tipo LIKE COALESCE(?, '%')) " // 1
                + "AND (Nombre LIKE COALESCE(?, '%')) " // 2
                + "AND (Apellidos LIKE COALESCE(?, '%')) " // 3
                + "AND (Email LIKE COALESCE(?, '%')) " // 4
                + "AND (COALESCE(Telefono, '') LIKE COALESCE(?, '%')) " // 5
                + "AND (Usuario LIKE COALESCE(?, '%')) " // 6
                + "AND (Direccion LIKE COALESCE(?, '%'))";   // 7

        ObservableList<Usuario> data = FXCollections.observableArrayList();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Configurar los parámetros para COALESCE
            pstmt.setString(1, fk_ClaseUser != null && !fk_ClaseUser.isEmpty() ? "%" + fk_ClaseUser + "%" : "%");
            pstmt.setString(2, Nombre != null && !Nombre.isEmpty() ? "%" + Nombre + "%" : "%");
            pstmt.setString(3, Apellidos != null && !Apellidos.isEmpty() ? "%" + Apellidos + "%" : "%");
            pstmt.setString(4, Email != null && !Email.isEmpty() ? "%" + Email + "%" : "%");
            pstmt.setString(5, Telefono != null && !Telefono.isEmpty() ? "%" + Telefono + "%" : "%");
            pstmt.setString(6, Usuario != null && !Usuario.isEmpty() ? "%" + Usuario + "%" : "%");
            pstmt.setString(7, Direccion != null && !Direccion.isEmpty() ? "%" + Direccion + "%" : "%");

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setNombre(rs.getString("Nombre"));
                user.setApellidos(rs.getString("Apellidos"));
                user.setTelefono(rs.getString("Telefono"));
                user.setEmail(rs.getString("Email"));
                user.setUsuario(rs.getString("Usuario"));
                user.setDireccion(rs.getString("Direccion"));

                // Mapear el tipo de usuario
                String claseUser = rs.getString("fk_tipo");
                user.setTipo(mapearTipoUsuario(claseUser));

                // Imprimir para depuración y agregar a la lista
                data.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios: " + e.getMessage());
        }

        return data;
    }

    private static Usuario.TipoUsuario mapearTipoUsuario(String claseUser) {
        if (claseUser == null) {
            return null; // O un valor predeterminado
        }
        switch (claseUser) {
            case "ALUMNO":
                return Usuario.TipoUsuario.ALUMNO;
            case "ADMINISTRADOR":
                return Usuario.TipoUsuario.ADMINISTRADOR;
            case "PROFESOR":
                return Usuario.TipoUsuario.PROFESOR;
            default:
                return null; // O Usuario.TipoUsuario.GENERICO
        }
    }

    public static void ActualizarUsuario(Connection conn, Usuario user) {
        List<String> asignaturasExistentes = new ArrayList<>();
        List<String> asignaturas = user.getAsignaturas();

        String sqlActualizar = "UPDATE usuario_tabla SET "
                + "Nombre = ?, Apellidos = ?, Direccion = ?, Telefono = ?, Foto=?, Email = ?, fk_tipo = ?, fechaAlta=?, fechaBaja = ? "
                + "WHERE Usuario = ?";

        String sqlAsignaturaUsuario = "SELECT asignatura FROM usuario_asignatura WHERE usuario = ?";

        String sqlInsertarAsignaturas = "INSERT INTO usuario_asignatura (usuario, asignatura) VALUES (?, ?)";
        String deleteSQL = "DELETE FROM usuario_asignatura WHERE usuario = ? AND asignatura = ?";

        try {
            // 1. Deshabilitar auto-commit
            conn.setAutoCommit(false);

            // Preparar sentencias
            try (PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar); PreparedStatement pstmtAsignaturaUsuario = conn.prepareStatement(sqlAsignaturaUsuario); PreparedStatement pstmtInsertarAsignatura = conn.prepareStatement(sqlInsertarAsignaturas); PreparedStatement pstmtEliminarAsignatura = conn.prepareStatement(deleteSQL)) {

                // 2. Actualizar usuario
                pstmtActualizar.setString(1, user.getNombre());
                pstmtActualizar.setString(2, user.getApellidos());
                pstmtActualizar.setString(3, user.getDireccion());
                pstmtActualizar.setString(4, user.getTelefono());
                pstmtActualizar.setBytes(5, user.getFoto());
                pstmtActualizar.setString(6, user.getEmail());

                if (user.getTipo() != null) {
                    pstmtActualizar.setString(7, user.getTipo().toString());
                } else {
                    pstmtActualizar.setNull(7, java.sql.Types.VARCHAR);
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                if (user.getFechaAlta() != null) {
                    pstmtActualizar.setString(8, formatter.format(user.getFechaAlta()));
                } else {
                    pstmtActualizar.setNull(8, java.sql.Types.TIMESTAMP);
                }

                if (user.getFechaBaja() != null) {
                    pstmtActualizar.setString(9, formatter.format(user.getFechaBaja()));
                } else {
                    pstmtActualizar.setNull(9, java.sql.Types.TIMESTAMP);
                }

                pstmtActualizar.setString(10, user.getUsuario());
                pstmtActualizar.executeUpdate();

                // 3. Obtener asignaturas existentes
                pstmtAsignaturaUsuario.setString(1, user.getUsuario());
                try (ResultSet rs = pstmtAsignaturaUsuario.executeQuery()) {
                    while (rs.next()) {
                        asignaturasExistentes.add(rs.getString("asignatura"));
                    }
                }

                // 4. Insertar nuevas asignaturas
                for (String asignatura : asignaturas) {
                    if (!asignaturasExistentes.contains(asignatura)) {
                        pstmtInsertarAsignatura.setString(1, user.getUsuario());
                        pstmtInsertarAsignatura.setString(2, asignatura);
                        pstmtInsertarAsignatura.executeUpdate();
                    }
                }

                // 5. Eliminar asignaturas que ya no están en la lista
                for (String asignatura : asignaturasExistentes) {
                    if (!asignaturas.contains(asignatura)) {
                        pstmtEliminarAsignatura.setString(1, user.getUsuario());
                        pstmtEliminarAsignatura.setString(2, asignatura);
                        pstmtEliminarAsignatura.executeUpdate();
                    }
                }

                // 6. Confirmar transacción
                conn.commit();
            } catch (SQLException ex) {
                // En caso de error, revertir transacción
                conn.rollback();
                Logger
                        .getLogger(Base_datos.class
                                .getName()).log(Level.SEVERE, "Error durante la transacción, cambios revertidos", ex);
            } finally {
                // Volver a habilitar auto-commit
                conn.setAutoCommit(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Base_datos.class
                    .getName()).log(Level.SEVERE, "Error al configurar la conexión", ex);
        }
    }

    public static void insertarFichero(Connection conn, String asignatura, String nombreArchivo, String rutaArchivo, String tema) {
        String sql = "INSERT INTO Material (Asignatura, Nombre, archivo,Tema) VALUES (?, ?, ?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql); FileInputStream fis = new FileInputStream(new File(rutaArchivo))) {

            // Establecer los parámetros
            pstmt.setString(1, asignatura); // Asignatura
            pstmt.setString(2, nombreArchivo); // Nombre del archivo
            pstmt.setBinaryStream(3, fis, (int) new File(rutaArchivo).length()); // Contenido del archivo
            pstmt.setString(4, tema);

            // Ejecutar la inserción
            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("El archivo se ha insertado correctamente.");
            }
        } catch (Exception e) {
            System.out.println("El archivo NO se ha insertado: " + e.getMessage());
        }
    }

    public static void descargarArchivo(Connection conn, String id, String nombreArchivo) {
        String consulta = "SELECT archivo FROM Material WHERE id = ?";
        FileOutputStream fos = null;

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setString(1, id); // Establece el nombre del archivo como parámetro

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener los datos binarios del archivo
                    byte[] archivoBytes = rs.getBytes("archivo");

                    if (archivoBytes != null) {
                        // Elegir dónde guardar el archivo
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setInitialFileName(nombreArchivo);
                        File file = fileChooser.showSaveDialog(null);

                        if (file != null) {
                            // Escribir los datos en el archivo seleccionado
                            fos = new FileOutputStream(file);
                            fos.write(archivoBytes);
                            fos.flush();

                            System.out.println("Archivo descargado correctamente: " + file.getAbsolutePath());
                        } else {
                            System.out.println("Operación cancelada por el usuario.");
                        }
                    } else {
                        System.out.println("El archivo no existe en la base de datos.");
                    }
                } else {
                    System.out.println("No se encontró un archivo con el nombre especificado.");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Base_datos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Base_datos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al acceder a la base de datos.");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void eliminarArchivo(Connection conn, String id, String nombreArchivo) {
        String consulta = "DELETE FROM Material WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setString(1, id); // Establece el nombre del archivo como parámetro

            int filasAfectadas = pstmt.executeUpdate(); // Ejecuta la consulta

            if (filasAfectadas > 0) {
                System.out.println("Archivo eliminado correctamente de la base de datos: " + nombreArchivo);
            } else {
                System.out.println("No se encontró un archivo con el nombre especificado: " + nombreArchivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el archivo de la base de datos.");
        }
    }

    public static void guardarExamenGeneradoEnBaseDeDatos(Connection conn, String asignatura, String tema, List<Map<String, String>> respuestas) {
        String sql = "INSERT INTO Examenes_tabla (asignatura,tema, pregunta, rverdadera, rf1, rf2, rf3) VALUES (?,?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Recorrer las respuestas
            for (Map<String, String> respuestasPanel : respuestas) {
                // Obtener los valores de las respuestas del mapa
                String pregunta = respuestasPanel.get("ta_pregunta");
                String rverdadera = respuestasPanel.get("ta_res_v");
                String rf1 = respuestasPanel.get("ta_resF1");
                String rf2 = respuestasPanel.get("ta_resF2");
                String rf3 = respuestasPanel.get("ta_resF3");

                // Configurar los parámetros de la consulta
                pstmt.setString(1, asignatura);
                pstmt.setString(2, tema);
                pstmt.setString(3, pregunta);
                pstmt.setString(4, rverdadera);
                pstmt.setString(5, rf1);
                pstmt.setString(6, rf2);
                pstmt.setString(7, rf3);

                // Ejecutar la inserción
                pstmt.addBatch();
            }

            // Ejecutar todos los inserts de una sola vez (mejor rendimiento)
            pstmt.executeBatch();
            System.out.println("Respuestas guardadas con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar las respuestas.");
        }
    }

    /**
     * Busca los temas de examen correspondientes a una asignatura en la base de
     * datos.
     *
     * @param conn La conexión activa a la base de datos.
     * @param asignatura El nombre de la asignatura cuyos temas se desean
     * buscar.
     * @return Una lista con los temas asociados con la asignatura, o una lista
     * vacía si no se encuentran temas.
     */
    public static List<String> BuscarExamenes(Connection conn, String asignatura) {
        // Definir la consulta SQL para obtener los temas de la asignatura
        String query = "SELECT tema FROM Examenes_tabla WHERE asignatura = ? GROUP BY tema";

        // Crear una lista para almacenar los temas encontrados
        List<String> temas = new ArrayList<>();

        // Intentar ejecutar la consulta y obtener los resultados
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establecer el valor del parámetro 'asignatura' en la consulta
            stmt.setString(1, asignatura);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Iterar sobre los resultados y agregar cada tema a la lista
                while (rs.next()) {
                    temas.add(rs.getString("tema"));
                }
            }
        } catch (SQLException e) {
            // Manejar posibles errores de SQL
            e.printStackTrace();
        }

        // Retornar la lista de temas (vacía si no se encontró ningún tema)
        return temas;
    }

    /**
     * Busca las preguntas de examen, junto con las respuestas correctas y
     * opciones incorrectas, para una asignatura y tema específicos. El mapa
     * contiene el número de la pregunta como clave y otro mapa con las
     * respuestas como valor.
     *
     * @param conn La conexión activa a la base de datos.
     * @param asignatura El nombre de la asignatura cuyo examen se desea
     * consultar.
     * @param tema El tema específico dentro de la asignatura cuyo examen se
     * desea consultar.
     * @return Un mapa con el número de pregunta como clave, y otro mapa con las
     * respuestas (correcta e incorrectas) como valores.
     */
    public static Map<Integer, Map<String, String>> Examen(Connection conn, String asignatura, String tema) {
        // Consulta SQL para obtener las preguntas y respuestas relacionadas con la asignatura y el tema
        String query = "SELECT pregunta, rverdadera, rf1, rf2, rf3 FROM Examenes_tabla WHERE asignatura = ? AND tema = ?";

        // Crear el mapa para almacenar las preguntas, con el número de pregunta como clave
        Map<Integer, Map<String, String>> examen = new HashMap<>();

        // Ejecutar la consulta y procesar los resultados
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Establecer los parámetros de la consulta
            stmt.setString(1, asignatura);
            stmt.setString(2, tema);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                int numeroPregunta = 1; // Variable para llevar el conteo del número de pregunta
                // Recorrer los resultados y agregarlos al mapa
                while (rs.next()) {
                    String pregunta = rs.getString("pregunta");
                    String respuestaCorrecta = rs.getString("rverdadera");
                    String respuestaIncorrecta1 = rs.getString("rf1");
                    String respuestaIncorrecta2 = rs.getString("rf2");
                    String respuestaIncorrecta3 = rs.getString("rf3");

                    // Crear un submapa para las respuestas
                    Map<String, String> respuestas = new HashMap<>();
                    respuestas.put("numero", numeroPregunta + "");
                    respuestas.put("Pregunta", pregunta);
                    respuestas.put("correcta", respuestaCorrecta);
                    respuestas.put("incorrecta1", respuestaIncorrecta1);
                    respuestas.put("incorrecta2", respuestaIncorrecta2);
                    respuestas.put("incorrecta3", respuestaIncorrecta3);

                    // Agregar el número de pregunta y las respuestas al mapa principal
                    examen.put(numeroPregunta, respuestas);

                    // Incrementar el número de pregunta
                    numeroPregunta++;
                }
            }
        } catch (SQLException e) {
            // Manejo de posibles errores de SQL
            e.printStackTrace();
        }

        // Retornar el mapa con las preguntas y respuestas (vacío si no hay resultados)
        return examen;
    }

    /**
     * Guarda un examen realizado en la tabla Examenes_Realizados_Tabla.
     *
     * @param conn La conexión a la base de datos.
     * @param asignatura La asignatura del examen.
     * @param tema El tema del examen.
     * @param nota La nota obtenida en el examen.
     * @param usuario El usuario que realizó el examen.
     */
    public static void GuardarExamenRealizado(Connection conn, String asignatura, String tema, String nota, String usuario) {
        String sql = "INSERT INTO Examenes_Realizados_Tabla (asignatura, tema, nota, usuario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Asignar los valores a los parámetros de la consulta
            pstmt.setString(1, asignatura);
            pstmt.setString(2, tema);
            pstmt.setString(3, nota);
            pstmt.setString(4, usuario);

            // Ejecutar la consulta
            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Examen guardado exitosamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el examen realizado: " + e.getMessage());
        }
    }

    /**
     * Obtiene una lista de temas de la tabla Examen_Realizados_Tablas según el
     * usuario y asignatura.
     *
     * @param conn La conexión a la base de datos.
     * @param usuario El usuario para filtrar la consulta.
     * @param asignatura La asignatura para filtrar la consulta.
     * @return Una lista de temas encontrados o una lista vacía si no hay
     * resultados.
     * @throws SQLException Si ocurre un error al realizar la consulta SQL.
     */
    public static List<String> obtenerTemasPorUsuarioYAsignatura(Connection conn, String usuario, String asignatura) throws SQLException {
        String sql = "SELECT tema FROM Examenes_Realizados_Tabla WHERE usuario = ? AND asignatura = ?";
        List<String> temas = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, asignatura);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    temas.add(rs.getString("tema"));
                }
            }
        }

        return temas;
    }

    /**
     * Obtiene una lista de exámenes de la tabla Examen_Realizados_Tablas según
     * el usuario y asignatura.
     *
     * @param conn La conexión a la base de datos.
     * @param usuario El usuario para filtrar la consulta.
     * @param asignatura La asignatura para filtrar la consulta.
     * @return Una lista de exámenes encontrados o una lista vacía si no hay
     * resultados.
     * @throws SQLException Si ocurre un error al realizar la consulta SQL.
     */
    public static List<examen> obtenerExamenesPorUsuarioYAsignatura(Connection conn, String usuario, String asignatura) throws SQLException {
        String sql = "SELECT nota, tema FROM Examenes_Realizados_Tabla WHERE usuario = ? AND asignatura = ? ORDER BY tema";
        List<examen> examenes = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, asignatura);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    double nota = rs.getDouble("nota");
                    String tema = rs.getString("tema");
                    examenes.add(new examen(tema, nota));
                }
            }
        }

        return examenes;
    }

    /**
     * Obtiene la nota más alta registrada para un usuario, asignatura y tema
     * específicos.
     *
     * @param conn La conexión activa a la base de datos.
     * @param usuario El nombre de usuario para el cual se quiere obtener la
     * nota.
     * @param asignatura El nombre de la asignatura del examen.
     * @param tema El tema específico del examen.
     * @return La nota más alta como un String, o un mensaje de error si no se
     * encuentran registros o si ocurre un problema.
     */
    public static String NotaExamenMax(Connection conn, String usuario, String asignatura, String tema) {
        // Definir la consulta SQL para obtener la nota más alta
        String sql = "SELECT MAX(nota) AS nota_maxima FROM Examenes_Realizados_Tabla WHERE usuario = ? AND asignatura = ? AND tema = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Establecer los parámetros de la consulta
            stmt.setString(1, usuario);
            stmt.setString(2, asignatura);
            stmt.setString(3, tema);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si se obtuvo un resultado
            if (rs.next()) {
                // Obtener la nota más alta como String
                double notaMaxima = rs.getDouble("nota_maxima");
                return String.valueOf(notaMaxima);  // Convertir la nota a String
            } else {
                return "No se encontraron registros para los parámetros especificados.";
            }

        } catch (SQLException e) {
            return "Error al obtener la nota más alta: " + e.getMessage();
        }
    }
}

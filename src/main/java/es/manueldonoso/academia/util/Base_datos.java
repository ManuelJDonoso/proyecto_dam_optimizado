/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import es.manueldonoso.academia.modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Base_datos {

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
     * @throws ClassNotFoundException si el controlador de SQLite no está
     * presente.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
    public static Connection conectarSqlite() {

        String DB_URL = "jdbc:sqlite:src/main/resources/db/db.db";

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Creando Conexion");
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
     * @throws SQLException Si ocurre un error durante la conexión o la
     * ejecución de las consultas SQL.
     * @throws NullPointerException Si el objeto `user` es `null` o cualquiera
     * de sus métodos `getUsuario()`, `getNombre()`, `getApellidos()` o
     * `getEmail()` retorna `null`.
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

}

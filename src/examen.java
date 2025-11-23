public class examen {

    public static final String ANSI_GRIS = "\u001B[90m"; // Gris brillante
    public static final String ANSI_RESET = "\u001B[0m"; // Resetear color
    public static final String ANSI_ROJO = "\u001B[31m"; // Texto rojo
    public static final String ANSI_VERDE = "\u001B[32m"; // Texto verde
    public static final String ANSI_AZUL = "\u001B[34m"; // Texto azul

    public static void main(String[] args) {

        String respuesta = "s";
        do {
            System.out.println("""
                    INTRODUCE EL CÓDIGO DEL PAQUETE PARA COMPROBAR SI ES CORRECTO
                    =============================================================
                    """);
            System.out.println(ANSI_GRIS + "CÓDIGO DE EJEMPLO: MAD123453" + ANSI_RESET);

            String codigo = System.console().readLine("CÓDIGO: ");
            if (sonTodasMayusculas(codigo) || sonTodosDigitos(codigo)) {
                System.out.println(ANSI_ROJO + "\nCÓDIGO INVÁLIDO!!!" + ANSI_RESET);
            } else if (esCodigoValido(codigo)) {
                System.out.println(ANSI_VERDE + "\nCÓDIGO VÁLIDO!!!" + ANSI_RESET);
            } else if (codigo.length() == 9 && !ultimoDígitoValid(codigo)) {
                System.out.println("\n" + ANSI_ROJO + "CODIGO INVÁLIDO!!!" + ANSI_RESET + ANSI_AZUL
                        + "\nCódigo corregido por el último dígito: " + ANSI_RESET + corregirCodigo(codigo));
            } else {
                System.out.println(ANSI_ROJO + "\nCÓDIGO INVÁLIDO!!!" + ANSI_RESET);
            }

            respuesta = System.console().readLine("""
                    \n¿Quieres salir o verificar otro código de paquete? salir/volver
                    =================================================================
                    """);
            if (respuesta.toLowerCase().charAt(0) == 'v') {
                limpiarConsola();
            }
        } while (respuesta.toLowerCase().charAt(0) != 's');

    }

    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // PRIMERA PARTE
    // ==========================================================================

    /**
     * Devuelve true si contiene un dígito y false si no lo contiende.
     * Se define como dígito un número que esté entre 0 y 9 incluyendo a los dos.
     * 
     * @param digito Carácter a evaluar
     * @return Verdadero si el caracter es un dígito y falso si no lo es
     */
    public static boolean esDigito(char digito) {
        if (digito >= '0' && digito <= '9') {
            return true;
        } else
            return false;
    }

    /**
     * Devuelve true si contiene una letra mayúscula entre A y Z incluyendo ambas,
     * 
     * @param letra
     * @return
     */
    public static boolean esMayuscula(char letra) {
        if (letra >= 'A' && letra <= 'Z') {
            return true;
        } else
            return false;
    }

    /**
     * Convierte un carácter a un número
     * 
     * @param numerochar
     * @return
     */
    public static int charADigito(char numerochar) {
        return numerochar - '0';
    }

    /**
     * Coge un String va convirtiendo cada caracter a número y lo va concatenando en
     * una variable que es la que luego devuelve.
     * 
     * @param numeroString
     * @return
     */
    public static int cadenaADigito(String numeroString) {
        int numero = 0;
        for (int i = 0; i < numeroString.length(); i++) {
            numero = numero * 10 + (charADigito(numeroString.charAt(i)));
        }
        return numero;
    }

    /**
     * Convierte un número a un carácter
     * 
     * @param numero
     * @return
     */
    public static char digitoAChar(int numero) {
        return (char) (numero + '0');
    }

    // SEGUNDA PARTE
    // ==========================================================================

    /**
     * Introduciendo una cadena, posicion principio y posición final forma una
     * cadena nueva con el sector que elijamos.
     * 
     * @param cadena
     * @param posicion1
     * @param posicion2
     * @return
     */
    public static String extraerSubcadena(String cadena, int posicion1, int posicion2) {
        String aux = "";
        for (int i = posicion1; i <= posicion2; i++) {
            aux = aux + cadena.charAt(i);
        }
        return aux;
    }

    /**
     * Introducendo una cadena estrae los 3 primero carácteres
     * 
     * @param cadena
     * @return
     */
    public static String extraerZona(String cadena) {
        return extraerSubcadena(cadena, 0, 2);
    }

    /**
     * Si detecta un número en la cadena estrae los siguientes 4 número incluyendo
     * el que haya detectado
     * 
     * @param codigo
     * @return
     */
    public static int extraerNumeroPaquetes(String codigo) {
        for (int i = 1; i <= codigo.length(); i++) {
            if (esDigito(codigo.charAt(i))) {
                return cadenaADigito(extraerSubcadena(codigo, i, i + 4));
            }
        }
        return 0;
    }

    /**
     * Extr
     * 
     * @param codigo
     * @return
     */
    public static char extraerDigitoControl(String codigo) {

        for (int i = 1; i <= codigo.length(); i++) {
            if (i == (codigo.length() - 1)) {
                return codigo.charAt(i);
            }
        }
        return '0';
    }

    // TERCERA PARTE
    // ==========================================================================

    /**
     * Introduce una cadena y te detecta si todas las letras son mayúsculas, si no
     * lo son devuelve false.
     * 
     * @param cadena
     * @return
     */
    public static boolean sonTodasMayusculas(String cadena) {

        for (int i = 0; i < cadena.length(); i++) {
            if (!esMayuscula(cadena.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Según una cadena detecta si todos son dígitos.
     * 
     * @param cadena
     * @return
     */
    public static boolean sonTodosDigitos(String cadena) {

        for (int i = 0; i < cadena.length(); i++) {
            if (!esDigito(cadena.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devuelve true si el código tiene exactamente 9 caracteres con el formato
     * LLLNNNNNC (3 mayúsculas + 5 dígitos + 1 dígito)
     * 
     * @param codigo
     * @return
     */
    public static boolean contieneFormatoValido(String codigo) {
        if (codigo.length() != 9) {
            return false;
        } else if (sonTodasMayusculas(codigo)) {
            return false;
        } else if (!sonTodasMayusculas(extraerZona(codigo))) {
            return false;
        } else if (!sonTodosDigitos("" + extraerNumeroPaquetes(codigo))) {
            return false;
        } else if (!esDigito((char) extraerDigitoControl(codigo))) {
            return false;
        } else {
            return true;
        }
    }

    // CUARTA PARTE
    // ==========================================================================

    /**
     * Esta función coge un carácter y según el valor de su código ASCII lo resta a
     * 64 porque 65 es el mínimo (65 = 'A') y devuelve un número de 1 a 26
     * 
     * @param caracter Un carácter en mayúsculas
     * @return Valor del carácter según su orden en el alfabeto de 1 (A) a 26 (Z)
     */
    public static int valorNumCharMayus(char caracter) {
        int numeroletra = (int) caracter - 64;
        return numeroletra;
    }

    /**
     * Suma según la cadena que le introduzcas cada valor de cada carácter teniendo
     * de referencia que A es 1 y Z es 26
     * 
     * @param cadena Introduce una cadena con letrás en mayúsculas
     * @return devuelve la suma de los valores de cada carácter
     */
    public static int sumarValorZonas(String cadena) {
        int suma = 0;
        for (int i = 0; i < 3; i++) {
            suma += valorNumCharMayus(cadena.charAt(i));
        }
        return suma;
    }

    /**
     * Suma únicamente 5 dígitos, coge un número y suma el primero, con el segundo,
     * este con el tercero....
     * 
     * @param numero Introduce un numero de 5 dígitos.
     * @return Devuelve la suma de cada digito con el siguiente
     */
    public static int sumarDigitos(int numero) {
        int suma = 0;
        for (int i = 0; i < 5; i++) {
            suma += numero % 10;
            numero /= 10;
        }
        return suma;
    }

    /**
     * Calcula cuál debería ser el último dígito de control.
     * 
     * @param codigo Introduce el código entero en String
     * @return Devuelve un Int con un número
     */
    public static int calcularDigitoControl(String codigo) {
        codigo = extraerSubcadena(codigo, 0, 7);

        return (sumarValorZonas(extraerSubcadena(codigo, 0, 2)) +
                sumarDigitos(cadenaADigito(extraerSubcadena(codigo, 3, 7)))) % 10;
    }

    public static boolean ultimoDígitoValid(String codigo) {
        if (calcularDigitoControl(codigo) == Character.getNumericValue(extraerDigitoControl(codigo))) {
            return true;
        } else {
            return false;
        }

    }

    // QUINTA PARTE
    // ==========================================================================

    public static boolean esCodigoValido(String codigo) {
        if (!contieneFormatoValido(codigo)) {
            return false;
        } else if (!ultimoDígitoValid(codigo)) {
            return false;
        } else {
            return true;
        }
    }

    public static String corregirCodigo(String cadena) {

        cadena = extraerSubcadena(cadena, 0, 7);
        return cadena + calcularDigitoControl(cadena);
    }

}

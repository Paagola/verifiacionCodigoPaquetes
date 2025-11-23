public class App {

    public static void main(String[] args) throws Exception {

        int numero = Integer.parseInt(System.console().readLine("Numero: "));

        System.out.println(esPrimo(numero));
        System.out.println(esCapicua(numero));
        System.out.println(siguientePrimo(numero));
        System.out.println(potencia(numero, 3));
        System.out.println(contarNums(numero));
        System.out.println(voltearNum(numero));
        System.out.println(digitosN(numero, 3));
        System.out.println(quitaPorDetras(numero, 3));
        System.out.println(quitaPorDelante(numero, 2));
        System.out.println(pegarPorDelante(numero, 8));
        System.out.println(pegaPorDetras(numero, 3));
        System.out.println(juntaNumeros(1111, 2222));
        System.out.println(trozoNumero(numero, 0, 3));
    }

    // =========================================================
    // FUNCIONES
    // =========================================================

    // FUNCIONES CON 3 FUNCIONES DENTRO
    // =========================================================
    /**
     * Esta función da la vuelta al número, quita los número que tenga que quitar
     * y luego vuelve a darle la vuelta al número.
     * 
     * @param numero
     * @param quitar
     * @return
     */
    public static int quitaPorDelante(int numero, int quitar) {
        int aux = voltearNum(quitaPorDetras(voltearNum(numero*10+1), quitar))/10;
        return aux;
    }

    // FUNCIONES CON 2 FUNCIONES DENTRO
    // =========================================================

    /**
     * Esta funcion une dos números introduciendo le primer número y
     * después del segundo que quieras unir detrás.
     * 
     * @param numero1
     * @param numero2
     * @return
     */
    public static long juntaNumeros(int numero1, int numero2) {

        int multiplicador = (int) potencia(10, contarNums(numero2));
        return numero1 * multiplicador + numero2;
    }

    /**
     * Según un número indicando las posiciones por delante que le quieras quitar el posicion1 y en posicion2
     * las posiciones por detrás que le quieras quitar.
     * @param numero
     * @param posicion1
     * @param posicion2
     * @return
     */
     public static int trozoNumero(int numero, int posicion1, int posicion2) {

        return quitaPorDelante(quitaPorDetras(numero, contarNums(numero)-((posicion2)+1)),posicion1);
    }

    // FUNCIONES CON 1 FUNCION DENTRO
    // =========================================================
    /**
     * Saber si un número es capicua introduciendo un int
     * 
     * @param numero
     * @return devuelve un boolean dando true si es capicua
     *         y un false si no lo es.
     */
    public static boolean esCapicua(int numero) {
        int auxReverse = voltearNum(numero);

        if (numero == auxReverse)
            return true;
        else
            return false;
    }

    /**
     * Calculamos el siguiente primo de un numero introducido
     * 
     * @param numero
     * @return
     */
    public static int siguientePrimo(int numero) {
        int aux = numero;
        boolean primo = false;

        while (!primo) {
            aux++;
            primo = esPrimo(aux);
        }
        return aux;
    }

    /**
     * Según un número y una posición averiguamos que dígito está en
     * esa posición de ese número.
     * 
     * @param numero
     * @param posicion
     * @return
     */
    public static int digitosN(int numero, int posicion) {
        int auxReverse = voltearNum(numero);
        int numero_posicion = 0;

        for (int i = 0; i < posicion; i++) {
            numero_posicion = auxReverse % 10;
            auxReverse /= 10;
        }
        return numero_posicion;
    }

    /**
     * Primero voltea el número, luego lo multiplica * 10 le añade
     * digito y lo vuelve a voltear para dejarlo normal
     * 
     * @param numero
     * @param digito
     * @return
     */
    public static int pegarPorDelante(int numero, int digito) {
        return voltearNum(voltearNum(numero) * 10 + digito);
    }

    // FUNCIONES SIN NINGUNA FUNCION DENTRO
    // =========================================================
    /**
     * Calculamos si un número es o no primo
     * 
     * @param numero
     * @return
     */
    public static boolean esPrimo(int numero) {
        boolean esono = true;
        if (numero == 0 || numero == 1) {
            esono = true;
        } else {
            for (int i = 2; i < (int) Math.sqrt(numero); i++) {
                if (numero % i == 0)
                    esono = false;
            }
        }
        return esono;
    }

    /**
     * Darle la vuelta a un número
     * 
     * @param numero
     * @return Devuelve un int con el número que se intrdución
     *         pero invertido.
     */
    public static int voltearNum(int numero) {
        int aux = numero;
        int auxReverse = 0;

        while (aux > 0) {
            auxReverse = auxReverse * 10 + (aux % 10);
            aux /= 10;
        }
        return auxReverse;
    }

    /**
     * Contarcuántos dígitos tiene un numero
     * 
     * @param numero
     * @return Devuele un int
     */
    public static int contarNums(int numero) {
        int contador = 0;
        while (numero != 0) {
            contador++;
            numero /= 10;
        }
        return contador;
    }

    /**
     * Calcular la potencia de un número según su base y su exponente
     * 
     * @param base
     * @param exponente
     * @return
     */
    public static long potencia(int base, int exponente) {
        long aux = 1;
        for (int i = 0; i < exponente; i++) {
            aux *= base;
        }
        return aux;
    }

    /**
     * Esta función quita posiciones por la derecha diciendo el numero
     * y luego las posiciones que quieras quitar.
     * 
     * @param numero
     * @param quitar
     * @return
     */
    public static int quitaPorDetras(int numero, int quitar) {

        int aux = numero;

        for (int i = 0; i < quitar; i++) {
            aux /= 10;
        }

        return aux;
    }

    /**
     * Añadir un dígito por detrás a un número
     * 
     * @param numero
     * @param digito
     * @return
     */
    public static int pegaPorDetras(int numero, int digito) {
        return numero * 10 + digito;
    }
}

# Validador de Códigos de Paquetes

Sistema de validación de códigos de paquetes con dígito de control desarrollado en Java.

## Descripción

Programa que valida códigos de paquetes siguiendo el formato LLLNNNNNC, calcula dígitos de control y ofrece corrección automática de errores.

## Formato del Código

Los códigos válidos siguen el formato: **LLLNNNNNC**

- **LLL**: 3 letras mayúsculas (zona de destino)
- **NNNNN**: 5 dígitos (número de paquete)
- **C**: 1 dígito de control

**Ejemplo válido**: `MAD123453`

## Algoritmo de Validación

El dígito de control se calcula mediante:

1. Sumar los valores de las letras de la zona (A=1, B=2, ..., Z=26)
2. Sumar los 5 dígitos del número de paquete
3. Aplicar módulo 10 al resultado total

**Ejemplo de cálculo** para `MAD12345`:
- Zona: M(13) + A(1) + D(4) = 18
- Número: 1 + 2 + 3 + 4 + 5 = 15
- Dígito control: (18 + 15) % 10 = **3**
- Código final: `MAD123453`

## Características

- Validación completa de formato y estructura
- Detección automática de errores en el dígito de control
- Corrección automática de códigos con dígito erróneo
- Interfaz colorizada para consola
- Limpieza automática de consola multiplataforma

## Requisitos

- Java JDK 11 o superior
- Terminal/Consola con soporte para secuencias de escape ANSI (colores)

## Instalación

```bash
javac examen.java
```

## Uso

```bash
java examen
```

### Ejemplo de Sesión

```
INTRODUCE EL CÓDIGO DEL PAQUETE PARA COMPROBAR SI ES CORRECTO
=============================================================

CÓDIGO DE EJEMPLO: MAD123453
CÓDIGO: MAD123453

CÓDIGO VÁLIDO!!!

¿Quieres salir o verificar otro código de paquete? salir/volver
=================================================================
```

### Corrección Automática

Si el código tiene el formato correcto pero el dígito de control es incorrecto:

```
CÓDIGO: MAD123459

CODIGO INVÁLIDO!!!
Código corregido por el último dígito: MAD123453
```

## Estructura del Código

### Funciones de Validación
- `esDigito(char)`: Verifica si un carácter es un dígito
- `esMayuscula(char)`: Verifica si un carácter es mayúscula
- `sonTodasMayusculas(String)`: Verifica que todos sean mayúsculas
- `sonTodosDigitos(String)`: Verifica que todos sean dígitos
- `contieneFormatoValido(String)`: Valida estructura LLLNNNNNC
- `ultimoDígitoValid(String)`: Verifica el dígito de control
- `esCodigoValido(String)`: Validación completa del código

### Funciones de Procesamiento
- `charADigito(char)`: Convierte carácter a número
- `cadenaADigito(String)`: Convierte cadena numérica a entero
- `extraerSubcadena(String, int, int)`: Extrae subcadena entre posiciones
- `extraerZona(String)`: Extrae las 3 primeras letras
- `extraerNumeroPaquetes(String)`: Extrae los 5 dígitos centrales
- `extraerDigitoControl(String)`: Extrae el último dígito
- `valorNumCharMayus(char)`: Obtiene valor alfabético (A=1...Z=26)
- `sumarValorZonas(String)`: Suma valores de las letras de zona
- `sumarDigitos(int)`: Suma los dígitos de un número
- `calcularDigitoControl(String)`: Calcula el dígito de control correcto
- `corregirCodigo(String)`: Genera código con dígito correcto

## Ejemplos de Uso

**Código Válido:**
```
Input: MAD123453
Output: CÓDIGO VÁLIDO!!!
```

**Formato Incorrecto:**
```
Input: MAD12345
Output: CÓDIGO INVÁLIDO!!!
```

**Dígito de Control Erróneo:**
```
Input: MAD123459
Output: CODIGO INVÁLIDO!!!
        Código corregido por el último dígito: MAD123453
```

## Notas Técnicas

- El programa requiere acceso a `System.console()` para funcionar correctamente
- Si se ejecuta desde un IDE, debe tener soporte para consola interactiva o ejecutarse desde terminal
- Utiliza códigos de color ANSI para la salida en consola
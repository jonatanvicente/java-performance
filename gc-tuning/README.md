

## Tuning del Garbage Collector (GC)

### Control de tránsito entre Young y Old Gen

| Parámetro                    | Descripción                                                                                        |
| ---------------------------- | -------------------------------------------------------------------------------------------------- |
| `-XX:NewRatio=N`             | Ratio entre Old y Young. Ej: `NewRatio=2` → Old será 2 veces más grande que Young.                 |
| `-XX:SurvivorRatio=N`        | Ratio entre Eden y cada Survivor. Ej: `SurvivorRatio=8` → Eden es 8x más grande que cada Survivor. |
| `-XX:MaxTenuringThreshold=N` | Número de GCs menores antes de promover a Old. Valores más altos → más tiempo en Survivor.         |
| `-XX:+AlwaysTenure`          | Promueve directamente a Old sin pasar por Survivor (poco usado).                                   |
| `-XX:+NeverTenure`           | Desactiva la promoción automática a Old (poco usado).                                              |


### Opciones específicas de GC G1

| Parámetro                              | Descripción                                                                |
| -------------------------------------- | -------------------------------------------------------------------------- |
| `-XX:InitiatingHeapOccupancyPercent=N` | % del heap ocupado que inicia un GC concurrente (recolección de Old).      |
| `-XX:G1ReservePercent=N`               | % reservado para evitar promociones fallidas.                              |
| `-XX:G1HeapRegionSize=N`               | Tamaño de región (regula granularidad de los espacios).                    |
| `-XX:TargetSurvivorRatio=N`            | % objetivo de ocupación en Survivor antes de promover a Old.               |
| `-XX:G1MixedGCLiveThresholdPercent=N`  | Umbral de ocupación de regiones Old para incluirlas en mezclas (Mixed GC). |

### Tuning adicional

| Parámetro                      | Significado                                                                                               |
| ------------------------------ | --------------------------------------------------------------------------------------------------------- |
| `-Xms` / `-Xmx`                | Tamaño inicial/máximo de la heap.                                                                         |
| `-Xmn`                         | Tamaño total del Young Gen.                                                                               |
| `-XX:PretenureSizeThreshold=N` | Objetos mayores a N bytes se asignan directamente en Old. Útil para grandes arrays. Solo para algunos GC. |
| `-XX:+UseTLAB`                 | Usa Thread Local Allocation Buffers (TLABs) para asignación rápida en Eden.                               |


#### Ejemplos

- Promoción rápida: **-XX:MaxTenuringThreshold=1**. Los objetos que sobreviven 1 GC se van a Old.
- Promoción lenta: **-XX:MaxTenuringThreshold=15**. Permite que los objetos vivan más en Survivor.
- Espacio pequeño en survivor: **-XX:SurvivorRatio=2**. Más objetos mueren rápido o se promueven más rápido por falta de espacio.
- Asignación directa a Old: **-XX:PretenureSizeThreshold=524288**. Objetos mayores a 512KB van directo a Old (solo en Serial/Parallel GC).
Formas de observación:
- VisualVM: pestaña "Monitor" y "Heap Dump" → generación de objetos.
- Java Mission Control (JMC): activar con -XX:+FlightRecorder, grabar eventos de GC y promociones.
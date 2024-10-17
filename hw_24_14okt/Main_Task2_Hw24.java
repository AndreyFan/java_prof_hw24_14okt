package de.telran.hw_24_14okt;

public class Main_Task2_Hw24 {
    // Запустите в 3 потока просчет суммы всех чисел, который без остатка
    // деляться на 17
    //в интервале от 0 до 10 млн. Показывайте на экран, как будет
    // накапливаться сумма по каждому потоку.
    //После запуска просчета, главный поток остановите на 1 секунду
    // и завершите его.
    //Все дочерние потоки должны автоматически закрыться. (Daemons)
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            int finalI = i; // переменная для идентификации потока
            threads[i] = new Thread(() -> {
                long sumNumberMultiple17 = 0; // переменная для накопления суммы чисел, к-е делятся на 17 целочисленно
                    for (int j = 16; j < 10_000_000; j++) {
                        if (j % 17 == 0) {
                            sumNumberMultiple17+=j ;
                            System.out.println(" Поток номер "+ threads[finalI].getName()+"  sumNumberMultiple17: " + sumNumberMultiple17);
                        }
                    }
            });
            threads[i].setDaemon(true); // делаем так чтобы потоки были остановлены после завершения основного потока программы
            threads[i].start();

        }

        try {
            Thread.sleep(1000);  // пауза основного потока программы
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Главный поток завершен.");

    }
}

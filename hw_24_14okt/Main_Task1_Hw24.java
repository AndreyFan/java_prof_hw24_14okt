package de.telran.hw_24_14okt;

import java.util.Arrays;
import java.util.Scanner;

public class Main_Task1_Hw24 {
    //  1 уровень сложности: 1. Пользователь с клавиатуры вводит цифру.
    //  Вы создаете динамически нужное количество потоков равное
    //введенной цифре, нумеруете их и запускаете на выполнение.
    //Каждый поток должен выводить свой номер на экран 100 раз с
    // интервалом 100 милисекунд перед каждым выводом.
    //Сделайте так, чтобы главный поток выполнения программы
    // не завершился до окончания работы всех дочерних потоков.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" введите желаемое количество потоков ");
        int count = scanner.nextInt();

        Thread[] threads = new Thread[count];

        for (int i = 0; i < count; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 100; j++) {
                        System.out.println("Поток номер: " + finalI);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        // Теперь делаем так, чтобы главный поток выполнения программы
        // не завершился до окончания работы всех дочерних потоков.
        Arrays.stream(threads).forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Нормальное завершение программы"); // просто метка нормального окончания задачи

    }

}

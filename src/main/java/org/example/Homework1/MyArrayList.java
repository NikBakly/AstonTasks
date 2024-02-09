package org.example.Homework1;

import java.util.Comparator;

public class MyArrayList<E> implements MyList<E> {
    /**
     * Размер списка по умолчанию при обычном создании
     */
    private static final int DEFAULT_SIZE = 10;

    private static final double DEFAULT_LIST_BOOST_FACTOR = 2.0;

    /**
     * Массив для хранения значений
     */
    private Object[] array;

    /**
     * Последний индекс, по которому нужно добавлять элемент в список
     */
    private int size;


    /**
     * Конструктор для создания списка с обычным размером
     */
    public MyArrayList() {
        this.array = new Object[DEFAULT_SIZE];
        setDefaultSize();
    }

    /**
     * Конструктор для создания списка с определенным размером, который передается в аргументе
     *
     * @param size размер будущего списка
     */
    public MyArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Illegal size: " + size);
        }
        this.array = new Object[size];
        setDefaultSize();
    }


    @Override
    public void add(E element) {
        listBoost();
        array[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        listBoost();
        // Сдвигаем элементы, начиная с index вправо на одну позицию
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element; // Вставляем новый элемент
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) array[index];
    }

    @Override
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                int numberOfMoved = size - i - 1;
                if (numberOfMoved > 0) {
                    System.arraycopy(array, i + 1, array, i, numberOfMoved);
                }
                array[--size] = null; // Очищаем последний элемент
            }
        }
    }

    @Override
    public void removeAll() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        setDefaultSize();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(array, 0, size - 1, c);
    }

    /**
     * Рекурсивный метод быстрой сортировки.
     *
     * @param arr  Массив, который нужно отсортировать
     * @param low  Начальный индекс
     * @param high Конечный индекс
     * @param c    Компаратор для сравнения элементов списка
     */
    private void quickSort(Object[] arr, int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pi = partition(arr, low, high, c);

            quickSort(arr, low, pi - 1, c);
            quickSort(arr, pi + 1, high, c);
        }
    }

    /**
     * Метод разделения для быстрой сортировки.
     *
     * @param arr  Массив, который нужно отсортировать
     * @param low  Начальный индекс
     * @param high Конечный индекс
     * @param c    Компаратор для сравнения элементов списка
     * @return Индекс элемента, который является опорным после разделения
     */
    private int partition(Object[] arr, int low, int high, Comparator<? super E> c) {
        E pivot = (E) arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (c.compare((E) arr[j], pivot) < 0) {
                i++;
                E temp = (E) arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        E temp = (E) arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Метод для инициализации переменной size
     */
    public void setDefaultSize() {
        this.size = 0;
    }


    /**
     * Метод для проверки значения индекса
     *
     * @param index индекс для проверки
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
        }
    }

    void listBoost() {
        if (size == array.length - 1) {
            int newSize = (int) (array.length * DEFAULT_LIST_BOOST_FACTOR);
            Object[] newArray = new Object[newSize];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public int size() {
        return size;
    }
}

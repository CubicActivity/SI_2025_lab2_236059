# Втора лабораториска вежба по Софтверско инженерство
</hr>

<h2>Aнгел Стојмановски, бр. на индекс 236059</h2>
</hr>

<h3>Control Flow Graph</h3>
<img src = "https://github.com/user-attachments/assets/5b94fe35-573b-4e2b-b5aa-af660c177e74">


<h3>Цикломатска комплексност</h3>
За да ја пресемтаме цикломатската комплескност, го броиме бројот на предикатни јазли, а тоа се 1, 4.2, 6, 8, 10, 14, 17.2, 19.2
или вкупно P = 8 предикатни јазли.<br> 
Според формулата V(G) = P + 1 = 8 + 1 = 9, според тоа цикломатската комплексност во графот е 9

<h3>Тест случаи според критериумот Every statement</h3>

    @Test
    void EveryStatementMinimal() {
        // Test 1: Null items list
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "any"));
        System.out.println("test 1 successful");

        // Test 2: Item with null name
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item(null, 1, 100, 0)), "any"));
        System.out.println("test 2 successful");

        // Test 3: Valid items with:
        // - price > 300
        // - discount > 0
        // - quantity > 10
        // - valid card
        List<Item> items = List.of(new Item("Item1", 15, 350, 0.1));
        double expected = (350 * 0.9 * 15) - 30; // (price*(1-disc)*qty) - penalty
        assertEquals(expected, SILab2.checkCart(items, "1234567890123456"), 0.001);
        System.out.println("test 3 successful");


        // Test 4: Invalid card number
        assertThrows(RuntimeException.class,() -> SILab2.checkCart(List.of(new Item("Item", 1, 100, 0)), "invalid"));
        System.out.println("test 4 successful");
    }


<h3>Тест случаи според критериумот Every path</h3>

    @Test
    void MultipleCondition() {
        // TC1: TXX (само price > 300)
        List<Item> tff = List.of(new Item("Item1", 5, 350, 0));
        assertEquals(350*5 - 30, SILab2.checkCart(tff, "1234567890123456"), 0.001);
        System.out.println("TXX test successful");

        // TC2: FTX (само discount > 0)
        List<Item> ftf = List.of(new Item("Item2", 5, 200, 0.1));
        assertEquals(200*0.9*5 - 30, SILab2.checkCart(ftf, "1234567890123456"), 0.001);
        System.out.println("FTX test successful");

        // TC3: FFT (само quantity > 10)
        List<Item> fft = List.of(new Item("Item3", 15, 200, 0));
        assertEquals(200*15 - 30, SILab2.checkCart(fft, "1234567890123456"), 0.001);
        System.out.println("FFT test successful");

        // TC4: FFF (сите false)
        List<Item> fff = List.of(new Item("Item4", 5, 200, 0));
        assertEquals(200*5, SILab2.checkCart(fff, "1234567890123456"), 0.001);
        System.out.println("FFF test successful");
    }    

<h3>Објаснување на напишаните unit tests</h3><p>Овие тестови ја проверуваат целосната функционалност на методот <code>SILab2.checkCart()</code> според два клучни критериуми:</p><h4>1. Every Statement Tests</h4> <p>Обезбедуваат дека <strong>секој линија код</strong> е извршена барем еднаш:</p> <ul> <li><strong>Тест 1 & 2</strong>: Ги проверуваат граничните случаи за невалидни влезови (null листа и null име)</li> <li><strong>Тест 3</strong>: Случај што ги активира сите услови (цена, попуст, количина) и валидна карта</li> <li><strong>Тест 4</strong>: Проверува невалидна карта</li> </ul><h4>2. Multiple Condition Tests</h4> <p>Ги тестираат сите релевантни комбинации на условите во:</p> <pre><code>if (price>300 || discount>0 || quantity>10)</code></pre> <ul> <li><strong>TXX/FTX/FFT</strong>: Секој услов тестиран независно</li> <li><strong>FFF</strong>: Контролен случај без активирани услови</li> </ul>

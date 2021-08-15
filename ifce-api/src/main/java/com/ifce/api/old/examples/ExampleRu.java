package com.ifce.api.old.examples;


import com.ifce.api.old.*;
import com.ifce.api.old.main.core.Monitor;
import com.ifce.api.old.main.format.PreFormat1;
import com.ifce.api.old.main.object.Dialog1;

import java.io.Serializable;

public final class ExampleRu {

    public static void pl(final String s) {
        Generator.pl(s);
    }

    public static void p(final String s) {
        Generator.p(s);
    }

    public static void main(final String[] args) throws Exception {
        final var create = new Generator();
        final var monitor = Monitor.getInstance();

        final PreFormat1 preFormat1 = create.PreFormat();
        preFormat1.setGameName("БОЯРКА СПИРТ И МАГИЯ или ПО СЛЕДАМ ВОЛШЕБСТВА");
        preFormat1.setSaveName("save");
        preFormat1.setUnDefininedWordIfNotContains(" - не вижу здесь чего-то похожего");
        preFormat1.setUnDefininedWordIfContains(" - это невозможно использовать");
        preFormat1.setUnDefininedWordUse(" - нужно попробовать что-то другое");
        create.setInstance(preFormat1);

        final var dlg1 = create.Dialog(
                "я начал разговор c дворецким"
        );
        {
            final var dlg1_1 = dlg1.addContinues(new Dialog1(
                    "Доложите о моем приезде.",
                    "Господин просил не мешать."
            ));
            final var dlg1_2 = dlg1.addContinues(new Dialog1(
                    "Впустите меня!",
                    "И не подумаю!"));
            final var dlg1_3 = dlg1.addContinues(new Dialog1(
                    "Где ваш хозяин?",
                    "Боюсь вам сегодня не назначено. Ступайте, сударь."
            ));
            {
                final var dlg1_1_1 = dlg1_3.addContinues(new Dialog1(
                        "Именем короля, впустите!",
                        "Я плохо слышу."));
                final var dlg1_1_2 = dlg1_3.addContinues(new Dialog1(
                        "Ну, смерд, отведаешь ты у меня плетей!",
                        "Кажется я начинаю вас понимать..."
                ));
                {
                    final var dlg1_1_2_1 = dlg1_1_2.addContinues(new Dialog1(
                            "И что дальше?",
                            (Phrase1 & Serializable) (room111, inventory111) -> {
                                p("Входите пожалуйста, здесь вам искренне рады!");
                                monitor.getDialogCurrent().stop();
                            }
                    ));
                }
            }
        }



        final var person = create.Person("Аруй");
        final var game = create.Game();
        game.add(person);
        final var inventory = create.Inventory();

        final var main_room = create.Room(
                "menu",
                "БОЯРКА СПИРТ И МАГИЯ или ПО СЛЕДАМ ВОЛШЕБСТВА",
                """
                        Волшебник Зазул жаден, так считает мой король, пославший меня, своего верного придворного, 
                        за средством от золотожорного дракона - "Друг мой Аруй, многие столетия наши земли не знали 
                        такого напастья, ненасытный змей разоряет Наше царство,только колдовская настойка багульника 
                        и благородного боярышника избавит нас от него". Цена её велика и было принято решение 
                        экспроприировать зелье, вот только прознав про это, Зазул сбежал как только ему доложили 
                        о моем приезде. Что ж, как ищейка мне придется идти по его следам, смекалкой и чутьем отыскать 
                        его, где бы этот скряга не спрятался."""
        );

        final Act1 butlerAct1 = (Act1 & Serializable) (room, inv) -> {
            dlg1.start();
            return true;
        };

        final var butler = create.Item(
                "дворецкий",
                false,
                "",
                "Передо мной стоит дворецкий, наглая ухмылка не сходит с его лица, не нравиться он мне.",
                "",
                "",
                ""
        );
        butler.add(butlerAct1);

        main_room.add(butler);

        //---//

        final var hall = create.Room(
                "Зал",
                "ЗАЛ",
                "Этот огромный зал является приемной волшебника, и по совместительству видимо складом рухляди."
        );

        final var wayHall = create.Way(hall, "в зал");
        main_room.add(wayHall);

        final var cabinet = create.Item(
                "шкаф",
                false,
                "",
                "Высокий черный шкаф, на дверцах изображения чудовищ.",
                "дверцы не открываются, хоть шкаф и большой не думаю что волшебник там",
                "",
                ""
        );

        final var table = create.Item(
                "стол",
                false,
                "",
                "Красивый резной стол.",
                "под столом пусто. Зазул, ау!",
                "",
                ""

        );

        final var trash = create.Item(
                "хлам",
                false,
                "",
                "Тут полно всякого хлама.",
                "там в завалах сам черт ногу сломит, не хочу там копаться, пойду где светло и чисто",
                "",
                ""
        );

        final var magnifier = create.Item(
                "лупа",
                true,
                "лупа",
                "Кажется на столе из под кипы книг торчит ручка лупы.",
                "я ее взял",
                "очень сильная лупа",
                "я использовал лупу"
        );

        final var note = create.Item(
                "записка",
                true,
                "записка",
                "Иголкой к столу пришпилена записка.",
                "Кажется это не иголка а миниатюрная сабля, а записочку возьму",
                "какие-то мелкие еле различимые каракули, это что записка для мышей?",
                "\n\"в рот, компот и банку шпрот\nпропади же обормот\"\nчто это может значить?"
        );

        final Use1 use1Note = (Use1 & Serializable) (room, items) -> {
            if (items.size() == 2 && items.contains(magnifier)) {
                pl("теперь можно различить буквы, вот что тут написано: " + note.getUseTxt());
                return true;
            }
            return false;
        };
        note.add(use1Note);

        hall.add(table, note, trash, cabinet);

        //---//

        final var labor = create.Room(
                "лаборатория",
                "ЛАБОРАТОРИЯ",
                """
                        Реторты и склянки, бадьи с водорослями, корзина живых лягушек, 
                        клетки на полу и на стенах с чудными тварями, стеклоглазы, рыгуны, покашливают и сопят, 
                        но все это плохо видно, едкая дымка нависает надо всем, 
                        ядовито-синим горят комфорки под колбами, это из колб с кипящим варевом 
                        поднимается этот туман, колдун явно только-что был здесь, пока не поймаю он так и продолжит 
                        пакостничать и расставлять ловушки."""
        );

        hall.add(create.Way(labor, "дверь"));
        labor.add(create.Way(hall, "обратно в зал"));

        final var hammer = create.Item(
                "молоток",
                true,
                "молоток",
                "В углу почему-то валяется молоток.",
                "я взял молоток",
                "тяжелый молоток",
                "я ударил молотком"
        );

        final var alcohol = create.Item(
                "бутыль",
                false,
                "",
                "У стены стоит большая, величиной с бочку, бутыль с прозрачной жидкостью, с этикеткой " +
                        "\"ОСНОВА ВСЕГО\".",
                "отвинтил крышку и чуть не свалился от резкого запаха, это спирт, так пахнет пятница мой друг",
                "",
                ""
        );

        final var key = create.Item(
                "ключ",
                true,
                "ключ",
                "Пол покрывает слой даже думать не хочу чего, рожь можно сеять, из под луковой шелухи " +
                        "блестит ключ.",
                "возьму этот ключ, может пригодиться",
                "надо бы поискать дверь к которой он подойдет",
                "ключ подошел к двери, я открыл её"
        );

        final var window = create.Item(
                "окно",
                false,
                "",
                "Огромное окно высотой в два человечьих роста.",
                "за окном лес до самого горизонта",
                "",
                ""
        );
        final Use1 use1Window = (Use1 & Serializable) (room, items) -> {
            if (items.size() == 2 && items.contains(hammer)) {
                pl("разнес окно в дребезги, в душное пространство лаборатории ворвался лесной ветер, ветер хвои, " +
                        "душицы и смородины, ветер странствий, едкий дым развеяло");
                labor.add(key);
                labor.add(magnifier);
                labor.remove(window);
                labor.setDescription(
                        "Реторты и склянки, бадьи с водорослями, корзина живых лягушек, " +
                                "клетки на полу и на стенах с чудными тварями, стеклоглазы. "
                );
                if (!inventory.isHave(hammer)) {
                    hammer.move(labor, inventory);
                }
                room.toStrRoom(inventory);
                return true;
            }
            return false;
        };
        window.add(use1Window);

        labor.add(hammer, alcohol, window);
        //---//

        final var panty = create.Room(
                "кладовка",
                "КЛАДОВКА",
                "Множество ящиков и шкафов, все они заполнены сушеными травами и прочими алхимическими " +
                        "реагентами"
        );
        final var wayPanty = create.Way(
                panty, "ржавая дверь"
        );
        wayPanty.setLock(true);
        wayPanty.setLockTxt("не открывается, может просто нужен ключ?");
        final Use1 use1WayPanty = (Use1 & Serializable) (room, items) -> {
            if (items.size() == 1 && items.contains(key)) {
                wayPanty.setLock(false);
                pl(key.getUseTxt());
                return true;
            }
            pl("это не поможет");
            return false;
        };
        wayPanty.add(use1WayPanty);
        labor.add(wayPanty);

        create.Core(main_room, inventory).start();
    }
}
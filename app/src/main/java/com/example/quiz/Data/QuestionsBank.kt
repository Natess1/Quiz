package com.example.quiz

object QuestionBank {
    const val QUESTIONS_PER_GAME = 10

    val all: List<Question> = listOf(
        TextQuestion(
            text = "Как называется процесс образования органических веществ из углекислого газа и воды на свету?",
            acceptedAnswers = listOf("Фотосинтез")
        ),
        TextQuestion(
            text = "Какой город является столицей Австралии?",
            acceptedAnswers = listOf("Канберра")
        ),
        TextQuestion(
            text = "Как называется наука о клетке?",
            acceptedAnswers = listOf("Цитология")
        ),
        TextQuestion(
            text = "Как называется самая длинная река Африки?",
            acceptedAnswers = listOf("Нил")
        ),
        TextQuestion(
            text = "Как называется органоид клетки, который вырабатывает энергию?",
            acceptedAnswers = listOf("Митохондрия", "Митохондрии")
        ),
        TextQuestion(
            text = "Какой город является столицей Канады?",
            acceptedAnswers = listOf("Оттава")
        ),
        TextQuestion(
            text = "Как называется наука о растениях?",
            acceptedAnswers = listOf("Ботаника")
        ),
        TextQuestion(
            text = "Как называется самая высокая гора мира?",
            acceptedAnswers = listOf("Эверест", "Джомолунгма")
        ),
        TextQuestion(
            text = "Как называется пигмент, придающий листьям зелёный цвет?",
            acceptedAnswers = listOf("Хлорофилл")
        ),
        TextQuestion(
            text = "Как называется самое глубокое озеро мира?",
            acceptedAnswers = listOf("Байкал")
        ),
        TextQuestion(
            text = "Как называется наука о животных?",
            acceptedAnswers = listOf("Зоология")
        ),
        TextQuestion(
            text = "Какой город является столицей Бразилии?",
            acceptedAnswers = listOf("Бразилиа", "Бразилия")
        ),
        TextQuestion(
            text = "Как называется жидкая часть крови?",
            acceptedAnswers = listOf("Плазма")
        ),
        TextQuestion(
            text = "Как называется самая большая жаркая пустыня мира?",
            acceptedAnswers = listOf("Сахара")
        ),
        TextQuestion(
            text = "Как называется деление клетки, при котором образуются две одинаковые дочерние клетки?",
            acceptedAnswers = listOf("Митоз")
        ),
        TextQuestion(
            text = "Какой город является столицей Японии?",
            acceptedAnswers = listOf("Токио")
        ),
        TextQuestion(
            text = "Как называется вещество в эритроцитах, которое переносит кислород?",
            acceptedAnswers = listOf("Гемоглобин")
        ),
        SingleChoiceQuestion(
            text = "Какой газ поглощают растения при фотосинтезе?",
            options = listOf("Кислород", "Углекислый газ", "Азот", "Водород"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "Какой океан самый большой?",
            options = listOf("Атлантический", "Индийский", "Тихий", "Северный Ледовитый"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Сколько камер в сердце человека?",
            options = listOf("2", "3", "4", "5"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какой материк самый маленький по площади?",
            options = listOf("Антарктида", "Австралия", "Европа", "Южная Америка"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "Что является основной единицей наследственной информации?",
            options = listOf("Ген", "Рибосома", "Митохондрия", "Лизосома"),
            correctIndex = 0
        ),
        SingleChoiceQuestion(
            text = "Какой орган является самым большим у человека?",
            options = listOf("Печень", "Кожа", "Мозг", "Лёгкие"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "Какая страна самая большая по площади?",
            options = listOf("Канада", "Китай", "США", "Россия"),
            correctIndex = 3
        ),
        SingleChoiceQuestion(
            text = "Сколько хромосом в соматической клетке человека?",
            options = listOf("23", "44", "46", "48"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какой витамин вырабатывается в коже под действием солнечного света?",
            options = listOf("A", "C", "D", "K"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какое животное является самым большим млекопитающим?",
            options = listOf("Слон", "Синий кит", "Кашалот", "Жираф"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "На каком материке протекает река Амазонка?",
            options = listOf("Африка", "Азия", "Южная Америка", "Северная Америка"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какая река самая длинная в Европе?",
            options = listOf("Дунай", "Волга", "Рейн", "Днепр"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "Что является основной структурной единицей живого организма?",
            options = listOf("Ткань", "Орган", "Клетка", "Молекула"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какая кость самая длинная в теле человека?",
            options = listOf("Плечевая", "Бедренная", "Большеберцовая", "Позвоночник"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "К какой группе животных относятся насекомые?",
            options = listOf("Моллюски", "Членистоногие", "Черви", "Рыбы"),
            correctIndex = 1
        ),
        SingleChoiceQuestion(
            text = "Какой океан омывает восточное побережье США?",
            options = listOf("Тихий", "Индийский", "Атлантический", "Северный Ледовитый"),
            correctIndex = 2
        ),
        SingleChoiceQuestion(
            text = "Какой пролив разделяет Евразию и Северную Америку?",
            options = listOf("Гибралтарский", "Берингов", "Магелланов", "Ла-Манш"),
            correctIndex = 1
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных животных относятся к млекопитающим?",
            options = listOf("Кит", "Летучая мышь", "Акула", "Пингвин"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных стран находятся в Южной Америке?",
            options = listOf("Перу", "Мексика", "Чили", "Колумбия"),
            correctIndices = setOf(0, 2, 3)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных объектов являются органоидами клетки?",
            options = listOf("Ядро", "Рибосома", "Печень", "Лёгкое"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных объектов являются пустынями?",
            options = listOf("Сахара", "Гоби", "Байкал", "Амазонка"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных организмов относятся к грибам?",
            options = listOf("Дрожжи", "Мукор", "Кишечная палочка", "Хлорелла"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных животных являются хищниками?",
            options = listOf("Лев", "Волк", "Заяц", "Корова"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных растений относятся к хвойным?",
            options = listOf("Сосна", "Ель", "Берёза", "Дуб"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных животных являются земноводными?",
            options = listOf("Лягушка", "Тритон", "Ящерица", "Крокодил"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных стран находятся в Европе?",
            options = listOf("Франция", "Италия", "Польша", "Индия"),
            correctIndices = setOf(0, 1, 2)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных объектов являются материками?",
            options = listOf("Африка", "Антарктида", "Гренландия", "Арктика"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных животных являются птицами?",
            options = listOf("Пингвин", "Страус", "Летучая мышь", "Дельфин"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие компоненты есть в клетках растений, но отсутствуют в клетках животных?",
            options = listOf("Клеточная стенка", "Хлоропласты", "Ядро", "Рибосомы"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных рек протекают в России?",
            options = listOf("Лена", "Енисей", "Нил", "Амазонка"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных органов относятся к пищеварительной системе?",
            options = listOf("Желудок", "Печень", "Сердце", "Лёгкие"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных объектов являются океанами?",
            options = listOf("Индийский", "Атлантический", "Средиземный", "Балтийский"),
            correctIndices = setOf(0, 1)
        ),
        MultiChoiceQuestion(
            text = "Какие из перечисленных животных являются насекомыми?",
            options = listOf("Пчела", "Муравей", "Паук", "Скорпион"),
            correctIndices = setOf(0, 1)
        )
    )

    fun randomGame(): List<Question> = all.shuffled().take(QUESTIONS_PER_GAME)
}
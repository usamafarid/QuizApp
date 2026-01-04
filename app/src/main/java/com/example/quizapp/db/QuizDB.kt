package com.example.quizapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp.R
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.model.LeaderBoardModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [LeaderBoardModel::class, QuestionModel::class, CategoryModel::class], version = 1)
abstract class QuizDB : RoomDatabase() {

    abstract fun leaderboardDao(): LeaderBoardDAO
    abstract fun questionDao(): QuestionDAO
    abstract fun categoryDao(): CategoryDAO

    companion object {
        @Volatile
        private var INSTANCE: QuizDB? = null

        fun getInstance(context: Context): QuizDB {
            // Using double-checked locking for thread safety to ensure only one instance is created.
            return INSTANCE ?: synchronized(this) {
                val instance = INSTANCE
                if (instance != null) {
                    return instance
                }
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDB::class.java,
                    "Quiz_database"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // This coroutine is scheduled. It runs after the DB is built and INSTANCE is available.
                        INSTANCE ?.let {
                            CoroutineScope(Dispatchers.IO).launch {
//                                val categoryList = listOf(
//                                    CategoryModel(1, R.drawable.imghtml, "HTML", "10 Question"),
//                                    CategoryModel(2, R.drawable.imgcss, "CSS", "10 Question"),
//                                    CategoryModel(3, R.drawable.imgjs, "JAVASCRIPT", "10 Question"),
//                                    CategoryModel(4, R.drawable.imgpython, "PYTHON", "10 Question"),
//                                    CategoryModel(5, R.drawable.php, "PHP", "10 Question")
//                                )
//                                it.categoryDao().insertCategoryList(categoryList)
//                                val questionList = mutableListOf<QuestionModel>()
                                // HTML Questions (cid=1)
//                                questionList.add(QuestionModel(0, "what does HTML stand for?", "Hyperlinks and Text Markup Language", "HyperText Markup Language", "Home Tool Markup Language", "Hyperlinking Text Making Language", "HyperText Markup Language", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Choose correct HTML element for largest heading.", "<h6>", "<h4>", "<h1>", "<head>", "<h1>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which tag is used to create a hyperlink?", "<link>", "<href>", "<a>", "<h>", "<a>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which HTML tag is used to insert an image?", "<img>", "<image>", "<src>", "<picture>", "<img>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which attribute specifies alternate text for an image?", "src", "title", "alt", "text", "alt", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which tag is used to insert a line break?", "<break>", "<br>", "<lb>", "<newline>", "<br>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which HTML element defines important text?", "<b>", "<em>", "<strong>", "<highlight>", "<strong>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "What is the correct HTML for creating a checkbox?", "<checkbox>", "<input type='''checkbox'''>", "<check>", "<option type='''checkbox'''>", "<input type='''checkbox'''>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "How can you make a numbered list?", "<ul>", "<ol>", "<list>", "<nl>", "<ol>", cid = 1, userSelected = null))
//                                questionList.add(QuestionModel(0, "What tag is used for table rows?", "<td>", "<tr>", "<th>", "<table>", "<tr>", cid = 1, userSelected = null))

                                // CSS Questions (cid=2)
//                                questionList.add(QuestionModel(0, "What does CSS stand for?", "Colorful Style Sheets", "Computer Style Sheets", "Cascading Style Sheets", "Creative Style System", "Cascading Style Sheets", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which property changes text color?", "font-color", "text-style", "color", "fgcolor", "color", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which is correct CSS syntax?", "{body:color=black;}", "{body;color:black;}", "body {color: black;}", "body:color=black", "body {color: black;}", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you add a background color?", "background-color: yellow;", "color-background: yellow;", "bg-color: yellow;", "background: yellow-color;", "background-color: yellow;", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which property controls the text size?", "text-style", "font-size", "text-size", "font", "font-size", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you make text bold?", "font: bold", "style: bold", "font-weight: bold", "weight: bold", "font-weight: bold", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you center text?", "text-align: center", "text-position: middle", "align: center", "font-align: center", "text-align: center", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which property adds space inside an element?", "margin", "padding", "border", "spacing", "padding", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which property adds space outside an element?", "margin", "padding", "border", "spacing", "margin", cid = 2, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which value makes text italic?", "style: italics", "font: italic", "font-style: italic", "text: italic", "font-style: italic", cid = 2, userSelected = null))

                                // JavaScript Questions (cid=3)
//                                questionList.add(QuestionModel(0, "Inside which HTML tag do we put JS code?", "<javascript>", "<js>", "<scripting>", "<script>", "<script>", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you write '''Hello World''' in alert box?", "alert('Hello World');", "alertBox('Hello World');", "msg('Hello World');", "alertBox=‘Hello World’", "alert('Hello World');", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you create a function in JS?", "function:myFunc()", "function = myFunc()", "function myFunc()", "create.myFunc()", "function myFunc()", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How to call a function named '''test'''?", "test();", "call.test();", "run test;", "run(test);", "test();", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you declare a variable?", "variable carName", "var carName;", "v carName;", "var = carName", "var carName;", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which operator is used to assign value?", "=", "==", "===", ":=", "=", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "What will typeof '''Hello''' return?", "object", "int", "string", "text", "string", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which keyword is used to create constant?", "let", "var", "const", "define", "const", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How to write an IF statement?", "if x > y then", "if (x > y)", "if x > y", "if: (x > y)", "if (x > y)", cid = 3, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you add a comment?", "// comment", "<!-- comment -->", "# comment", "* comment *", "// comment", cid = 3, userSelected = null))

                                // Python Questions (cid=4)
//                                questionList.add(QuestionModel(0, "Who developed Python?", "Guido van Rossum", "Dennis Ritchie", "James Gosling", "Elon Musk", "Guido van Rossum", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "What is the correct extension?", ".pyth", ".pt", ".p", ".py", ".py", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which keyword defines a function?", "def", "func", "define", "method", "def", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "How to write a comment?", "# comment", "// comment", "* comment *", "<!-- comment -->", "# comment", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which function outputs data?", "write()", "display()", "show()", "print()", "print()", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you create a list?", "[one,two,three]", "(one,two,three)", "{one,two,three}", "<one,two,three>", "[one,two,three]", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which operator is used for exponentiation?", "**", "^", "pow", "exp", "**", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "What keyword is used to loop?", "repeat", "forEach", "for", "loop", "for", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "What symbol is used for comments?", "#", "//", "/* */", "<!-- -->", "#", cid = 4, userSelected = null))
//                                questionList.add(QuestionModel(0, "What is the output of len('''abc''')?", "four", "zero", "three", "two", "three", cid = 4, userSelected = null))

                                // PHP Questions (cid=5)
//                                questionList.add(QuestionModel(0, "What does PHP stand for?", "Private Home Page", "Hypertext Preprocessor", "Personal Hypertext Processor", "Pre Hypertext Page", "Hypertext Preprocessor", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "PHP files have which extension?", ".html", ".ph", ".php", ".xml", ".php", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which symbol is used to start variable?", "%", "&", "$", "@", "$", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which version of PHP introduced OOP?", "three", "five", "six", "four", "five", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "How to output text in PHP?", "echo()", "display()", "echo", "printf", "echo", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which keyword defines a function?", "func", "function:", "function", "def", "function", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "How do you write single-line comments?", "/* */", "//", "#", "<!-- -->", "//", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "What is used to include a file?", "import", "insert", "include", "load", "include", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "Which superglobal holds form data?", "_SERVER", "_POST", "_FORM", "_DATA", "_POST", cid = 5, userSelected = null))
//                                questionList.add(QuestionModel(0, "What is correct way to end PHP statements?", ";", ".", ":", ",", ";", cid = 5, userSelected = null))

//                                questionList.forEach { questionModel ->
//                                    it.questionDao().insertQuestions(questionModel)
//                                }
                            }
                        }
                    }
                })
                .build()

                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
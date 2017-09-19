package com.bookshop;

import com.bookshop.model.books.Book;
import com.bookshop.model.books.BookDescription;
import com.bookshop.model.category.Category;
import com.bookshop.model.category.CategoryDescription;
import com.bookshop.model.illustrations.Illustration;

import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BooksCreator {

  public void book1(Session session) {
    Book book = new Book();

    //Creating book description
    BookDescription bookDescription1 = new BookDescription();
    bookDescription1.setDescription("Inheritance is a fundamental concept of object-oriented "
        + "programming and Java. Relational databases have no concept of inheritance, so "
        + "persisting inheritance in a database can be tricky. Because relational databases "
        + "have no concept of inheritance, there is no standard way of implementing inheritance "
        + "in database, so the hardest part of persisting inheritance is choosing how to represent"
        + " the inheritance in the database.");

    bookDescription1.setLanguage("english");
    bookDescription1.setBookTitle("Spring Persistence with Hibernate");
    bookDescription1.setBookAuthor("Paul Fisher, Brian D.Murphy");
    bookDescription1.setBookPublishingYear("2010");
    bookDescription1.setBook(book);

    //Creating category and category description
    CategoryDescription categoryDescription1 = new CategoryDescription();
    categoryDescription1.setDescription("Раздел «Компьютерная литература» интернет – магазина "
        + "включает в себя книги по таким вопросам: операционные системы, программы для работы "
        + "с графикой, сети и интернет, компьютерная безопасность, программирование, офисные "
        + "программы, строение компьютера. ");
    categoryDescription1.setCategoryTitle("Компьютерная литература");
    Category category = new Category();
    category.setBook(book);
    categoryDescription1.setCategory(category);
    categoryDescription1.setLanguage("russian");

    //Creating image
    Illustration illustration = new Illustration();
    illustration.setIllustrationTitle("Spring Persistence with Hibernate");
    illustration.setIllustrationUrl("/book.jpeg");
    illustration.setBook(book);

    //Creating book
    Set<BookDescription> bookDescriptions = new HashSet<>();
    bookDescriptions.add(bookDescription1);
    book.setBookDescriptions(bookDescriptions);
    Set<Illustration> illustrations = new HashSet<>();
    illustrations.add(illustration);
    book.setBookIllustrations(illustrations);
    book.setPrice(BigDecimal.valueOf(10.5));
    book.setIsbn("ISBN 978-2-2661-1115-6");

    session.save(book);
    session.save(bookDescription1);
    session.save(category);
    session.save(categoryDescription1);
    session.save(illustration);
  }

  public void book2(Session session) {
    Book book = new Book();

    //Creating book description
    BookDescription bookDescription1 = new BookDescription();
    bookDescription1.setDescription("Более 10 лет первое издание этой книги считалось одним из"
        + " лучших практических руководств по программированию. Сейчас эта книга полностью обновлена"
        + " с учетом современных тенденций и технологий и дополнена сотнями новых примеров,"
        + " иллюстрирующих искусство и науку программирования. Опираясь на академические исследования, "
        + "с одной стороны, и практический опыт коммерческих разработок ПО — с другой, автор "
        + "синтезировал из самых эффективных методик и наиболее эффективных принципов ясное "
        + "прагматичное руководство. Каков бы ни был ваш профессиональный уровень, с какими бы "
        + "средствами разработками вы ни работали, какова бы ни была сложность вашего проекта, "
        + "в этой книге вы найдете нужную информацию, она заставит вас размышлять и поможет создать"
        + " совершенный код.\n"
        + "Книга состоит из 35 глав, предметного указателя и библиографии.");

    bookDescription1.setLanguage("russian");
    bookDescription1.setBookTitle("Совершенный код");
    bookDescription1.setBookAuthor("Стив Макконнелл");
    bookDescription1.setBookPublishingYear("2010");
    bookDescription1.setBook(book);

    //Creating category and category description
    CategoryDescription categoryDescription1 = new CategoryDescription();
    categoryDescription1.setDescription("Раздел «Компьютерная литература» интернет – магазина "
        + "включает в себя книги по таким вопросам: операционные системы, программы для работы "
        + "с графикой, сети и интернет, компьютерная безопасность, программирование, офисные "
        + "программы, строение компьютера. ");
    categoryDescription1.setCategoryTitle("Компьютерная литература");
    Category category = new Category();
    category.setBook(book);
    categoryDescription1.setCategory(category);
    categoryDescription1.setLanguage("russian");

    //Creating image
    Illustration illustration = new Illustration();
    illustration.setIllustrationTitle("Code complete");
    illustration.setIllustrationUrl("/book1.jpeg");
    illustration.setBook(book);

    //Creating book
    Set<BookDescription> bookDescriptions = new HashSet<>();
    bookDescriptions.add(bookDescription1);
    book.setBookDescriptions(bookDescriptions);
    Set<Illustration> illustrations = new HashSet<>();
    illustrations.add(illustration);
    book.setBookIllustrations(illustrations);
    book.setPrice(BigDecimal.valueOf(54.98));
    book.setIsbn("ISBN 978-5-7502-0064-1");

    session.save(book);
    session.save(bookDescription1);
    session.save(category);
    session.save(categoryDescription1);
    session.save(illustration);
  }

  public void book3(Session session) {
    Book book = new Book();

    //Creating book description
    BookDescription bookDescription1 = new BookDescription();
    bookDescription1.setDescription("В мире постоянно кто-то сталкивается с такими же проблемами"
        + " программирования, которые возникают и у вас. Многие разработчики решают совершенно"
        + " идентичные задачи и находят похожие решения. Если вы не хотите изобретать велосипед, "
        + "используйте готовые шаблоны (паттерны) проектирования, работе с которыми посвящена эта"
        + " книга. Паттерны появились, потому что многие разработчики искали пути повышения гибкости"
        + " и степени повторного использования своих программ. Найденные решения воплощены в краткой "
        + "и легко применимой на практике форме. Особенностью данного издания является уникальный"
        + " способ подачи материала, выделяющий серию «Head First» издательства O'Reilly в ряду"
        + " множества скучных книг, посвященных программированию. Книга будет интересна широкому"
        + " кругу веб-разработчиков, от начинающих до профессионалов, желающих освоить работу с"
        + " паттернами проектирования.");

    bookDescription1.setLanguage("russian");
    bookDescription1.setBookTitle("Паттерны проектирования");
    bookDescription1.setBookAuthor("Эрик Фримен, Элизабет Фримен");
    bookDescription1.setBookPublishingYear("2011");
    bookDescription1.setBook(book);

    //Creating category and category description
    CategoryDescription categoryDescription1 = new CategoryDescription();
    categoryDescription1.setDescription("Раздел «Компьютерная литература» интернет – магазина "
        + "включает в себя книги по таким вопросам: операционные системы, программы для работы "
        + "с графикой, сети и интернет, компьютерная безопасность, программирование, офисные "
        + "программы, строение компьютера. ");
    categoryDescription1.setCategoryTitle("Компьютерная литература");
    Category category = new Category();
    category.setBook(book);
    categoryDescription1.setCategory(category);
    categoryDescription1.setLanguage("russian");

    //Creating image
    Illustration illustration = new Illustration();
    illustration.setIllustrationTitle("Head first design patterns");
    illustration.setIllustrationUrl("/book3.jpeg");
    illustration.setBook(book);

    //Creating book
    Set<BookDescription> bookDescriptions = new HashSet<>();
    bookDescriptions.add(bookDescription1);
    book.setBookDescriptions(bookDescriptions);
    Set<Illustration> illustrations = new HashSet<>();
    illustrations.add(illustration);
    book.setBookIllustrations(illustrations);
    book.setPrice(BigDecimal.valueOf(37.0));
    book.setIsbn("ISBN 978-5-459-00435-9");

    session.save(book);
    session.save(bookDescription1);
    session.save(category);
    session.save(categoryDescription1);
    session.save(illustration);
  }
}

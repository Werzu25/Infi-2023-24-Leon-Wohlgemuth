package org.example;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DBWrapper {
    private static String DATABASE_URL = "jdbc:mysql://localhost/";
    private Dao<Customer,Integer> customerDao;
    private Dao<LibraryWorker,Integer> libraryWorkerDao;
    private Dao<Book,Integer> bookDao;
    private Dao<BorrowedBook,Integer> borrowedBookDao;


    public void intiDB() {
        ConnectionSource connectionSource = null;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        try {
            System.out.println("Database Name: ");
            String dbName = scanner.nextLine();
            DATABASE_URL += dbName;
            System.out.println("Username: ");
            String password = scanner.nextLine();
            System.out.println("Password: ");
            String username = scanner.nextLine();
            connectionSource = new JdbcConnectionSource(DATABASE_URL,username,password);
            setupDatabase(connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupDatabase(ConnectionSource connectionSource) throws Exception {
        customerDao = DaoManager.createDao(connectionSource, Customer.class);
        libraryWorkerDao = DaoManager.createDao(connectionSource, LibraryWorker.class);
        bookDao = DaoManager.createDao(connectionSource, Book.class);
        borrowedBookDao = DaoManager.createDao(connectionSource, BorrowedBook.class);

        TableUtils.createTableIfNotExists(connectionSource, Customer.class);
        TableUtils.createTableIfNotExists(connectionSource, LibraryWorker.class);
        TableUtils.createTableIfNotExists(connectionSource, Book.class);
        TableUtils.createTableIfNotExists(connectionSource, BorrowedBook.class);
    }
    public void insertTable(Object object) {
        try {
            if (object instanceof Customer) {
                customerDao.create((Customer) object);
            } else if (object instanceof LibraryWorker) {
                libraryWorkerDao.create((LibraryWorker) object);
            } else if (object instanceof Book) {
                bookDao.create((Book) object);
            } else if (object instanceof BorrowedBook ) {
                BorrowedBook insertedBook = (BorrowedBook) object;
                if (borrowedBookDao.queryForMatching(insertedBook).isEmpty()) {
                    borrowedBookDao.create(insertedBook);
                } else {
                    System.out.println("Book already borrowed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEntry(Object object, int id) {
        try {
            if (object instanceof Customer) {
                customerDao.updateId((Customer) object,id);
            } else if (object instanceof LibraryWorker) {
                libraryWorkerDao.updateId((LibraryWorker) object,id);
            } else if (object instanceof Book) {
                bookDao.updateId((Book) object,id);
            } else if (object instanceof BorrowedBook) {
                borrowedBookDao.updateId((BorrowedBook) object,id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(Object object, int id) {
        try {
            if (object instanceof Customer) {
                customerDao.deleteById(id);
            } else if (object instanceof LibraryWorker) {
                libraryWorkerDao.deleteById(id);
            } else if (object instanceof Book) {
                bookDao.deleteById(id);
            } else if (object instanceof BorrowedBook) {
                BorrowedBook borrowedBook = borrowedBookDao.queryForMatching((BorrowedBook) object).getFirst();
                borrowedBookDao.deleteById(borrowedBook.getBorrowId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(Object object) {
        try {
            if (object instanceof Customer) {
                for (Customer customer : customerDao.queryForAll()) {
                    System.out.println(customer);
                }
            } else if (object instanceof LibraryWorker) {
                for (LibraryWorker libraryWorker : libraryWorkerDao.queryForAll()) {
                    System.out.println(libraryWorker);
                }
            } else if (object instanceof Book) {
                for (Book book : bookDao.queryForAll()) {
                    System.out.println(book);
                }
            } else if (object instanceof BorrowedBook) {
                for (BorrowedBook borrowedBook : borrowedBookDao.queryForAll()) {
                    System.out.println(borrowedBook);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<?> getEntries(Object object) {
        try {
            if (object instanceof Customer) {
                return customerDao.queryForAll();
            } else if (object instanceof LibraryWorker) {
                return libraryWorkerDao.queryForAll();
            } else if (object instanceof Book) {
                return bookDao.queryForAll();
            } else if (object instanceof BorrowedBook) {
                return borrowedBookDao.queryForAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getEntriesById(Object object, int id) {
        try {
            if (object instanceof Customer) {
                return customerDao.queryForId(id);
            } else if (object instanceof LibraryWorker) {
                return libraryWorkerDao.queryForId(id);
            } else if (object instanceof Book) {
                return bookDao.queryForId(id);
            } else if (object instanceof BorrowedBook) {
                return borrowedBookDao.queryForId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<?> joinEntries(List<?> joinedTables) {
        if (joinedTables.getFirst() instanceof BorrowedBook) {
            try {
                QueryBuilder<BorrowedBook, Integer> queryBuilder = borrowedBookDao.queryBuilder();
                ArrayList<QueryBuilder<?,Integer>> queryBuilders = new ArrayList<>();
                Where<BorrowedBook,Integer> where = null;
                for (Object object : joinedTables) {
                    if (object instanceof Customer) {
                        where = queryBuilder.where();
                        where.eq("customerId",((Customer) object).getCustomerId());
                        queryBuilders.add(customerDao.queryBuilder());
                    } else if (object instanceof Book) {
                        where = queryBuilder.where();
                        where.eq("bookId",((Book) object).getBookId());
                        queryBuilders.add(bookDao.queryBuilder());
                    }
                }
                if (queryBuilders.getFirst() != null) {
                    return null;
                }
                queryBuilder.join(queryBuilders.getFirst()).setWhere(where);
                return queryBuilder.query();
            } catch (NoSuchElementException e) {
                System.out.println("No such element");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void writeList(List<Object> list) {
        if (list.getFirst() instanceof Customer) {
            for (Object object : list) {
                insertTable(object);
            }
        } else if (list.getFirst() instanceof LibraryWorker) {
            for (Object object : list) {
                insertTable(object);
            }
        } else if (list.getFirst() instanceof Book) {
            for (Object object : list) {
                insertTable(object);
            }
        } else if (list.getFirst() instanceof BorrowedBook) {
            for (Object object : list) {
                insertTable(object);
            }
        }
    }
}

package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateMysqlDatabase implements DatabaseAdapter {

    private SessionFactory sessionFactory;

    public HibernateMysqlDatabase() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Override
    public int newGame() {
        if (sessionFactory != null) {
            GameEntity game = new GameEntity();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(game);
            session.getTransaction().commit();
            session.close();
            return game.getId();
        } else {
            return 0;
        }
    }

    @Override
    public void move(int game, String player, int x, int y) {
        if (sessionFactory != null) {
            MoveEntity move = new MoveEntity(game,player,"place_stone",x,y);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(move);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void pass(int game, String player) {
        if (sessionFactory != null) {
            MoveEntity move = new MoveEntity(game,player,"pass");
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(move);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void finishGame(int id, String status) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            GameEntity game = session.load(GameEntity.class,id);
            game.setStatus(status);
            session.getTransaction().commit();
            session.close();
        }
    }
}

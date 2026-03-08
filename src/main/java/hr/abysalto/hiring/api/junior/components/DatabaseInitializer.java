package hr.abysalto.hiring.api.junior.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private boolean dataInitialized = false;

	public boolean isDataInitialized() {
		return this.dataInitialized;
	}

	public void initialize() {
		initTables();
		initData();
		this.dataInitialized = true;
	}

	private void initTables() {
		this.jdbcTemplate.execute("""
			 CREATE TABLE buyer (
				 buyer_id INT auto_increment PRIMARY KEY,
				 first_name varchar(100) NOT NULL,
				 last_name varchar(100) NOT NULL,
				 title varchar(100) NULL
			 )
 		""");

		this.jdbcTemplate.execute("""
			 CREATE TABLE buyer_address (
				 buyer_address_id INT auto_increment PRIMARY KEY,
				 city varchar(100) NOT NULL,
				 street varchar(100) NOT NULL,
				 home_number varchar(100) NULL
			 )
 		""");

		this.jdbcTemplate.execute("""
			 CREATE TABLE orders (
				 order_nr INT auto_increment PRIMARY KEY,
				 buyer_id int NOT NULL,
				 order_status varchar(32) NOT NULL,
				 order_time datetime NOT NULL,
				 delivery_address_id INT NOT NULL,
				 contact_number varchar(100) NULL,
				 currency varchar(50) NULL,
				 note varchar(255),
				 payment_option VARCHAR(50) NULL,
				 total_price decimal,
				 CONSTRAINT FK_order_to_buyer FOREIGN KEY (buyer_id) REFERENCES buyer (buyer_id),
				 CONSTRAINT FK_order_to_delivery_address FOREIGN KEY (delivery_address_id) REFERENCES buyer_address (buyer_address_id)
			 )
 		""");

		this.jdbcTemplate.execute("""
			 CREATE TABLE order_item (
				 order_item_id INT auto_increment PRIMARY KEY,
				 order_nr int NOT NULL,
				 item_nt smallint NOT NULL,
				 name varchar(100) NOT NULL,
				 quantity smallint NOT NULL,
				 price decimal,
				 CONSTRAINT UC_order_items UNIQUE (order_item_id, order_nr),
				 CONSTRAINT FK_order_item_to_order FOREIGN KEY (order_nr) REFERENCES orders (order_nr)
			 )
 		""");
	}

	private void initData() {
		this.jdbcTemplate.execute("INSERT INTO buyer (first_name, last_name, title) VALUES ('Jabba', 'Hutt', 'the')");
		this.jdbcTemplate.execute("INSERT INTO buyer (first_name, last_name, title) VALUES ('Anakin', 'Skywalker', NULL)");
		this.jdbcTemplate.execute("INSERT INTO buyer (first_name, last_name, title) VALUES ('Jar Jar', 'Binks', NULL)");
		this.jdbcTemplate.execute("INSERT INTO buyer (first_name, last_name, title) VALUES ('Han', 'Solo', NULL)");
		this.jdbcTemplate.execute("INSERT INTO buyer (first_name, last_name, title) VALUES ('Leia', 'Organa', 'Princess')");

		this.jdbcTemplate.execute("INSERT INTO buyer_address (city, street, home_number) VALUES ('ZG', 'Organa', '12')");
		this.jdbcTemplate.execute("INSERT INTO buyer_address (city, street, home_number) VALUES ('BG', 'Adresa', '13')");
		this.jdbcTemplate.execute("INSERT INTO buyer_address (city, street, home_number) VALUES ('JK', 'Lepa', '14')");
		
		this.jdbcTemplate.execute("INSERT INTO orders (buyer_id, order_status, order_time, delivery_address_id, contact_number, currency, payment_option, total_price)VALUES (1, 'PREPARING', CURRENT_TIMESTAMP, 1, '123456789', 'EUR', 'CASH' ,25.50)");
		this.jdbcTemplate.execute("INSERT INTO orders (buyer_id, order_status, order_time, delivery_address_id, contact_number, currency, payment_option, total_price) VALUES (2, 'DONE', CURRENT_TIMESTAMP, 2, '1000000', 'EUR', 'CARD_UPFRONT',12.50)");

		this.jdbcTemplate.execute("INSERT INTO order_item (order_nr, item_nt, name, quantity, price) VALUES (1, 1, 'Pizza Margherita', 2, 10.00)");
		this.jdbcTemplate.execute("INSERT INTO order_item (order_nr, item_nt, name, quantity, price) VALUES (2, 2, 'Coca Cola', 1, 5.50)");

		this.jdbcTemplate.execute("INSERT INTO order_item (order_nr, item_nt, name, quantity, price) VALUES (1, 1, 'Pizza Margherita', 1, 10.00)");
		this.jdbcTemplate.execute("INSERT INTO order_item (order_nr, item_nt, name, quantity, price) VALUES (1, 2, 'Coca Cola', 1, 5.50)");
		this.jdbcTemplate.execute("INSERT INTO order_item (order_nr, item_nt, name, quantity, price) VALUES (1, 3, 'Burger', 1, 12.00)");
}
}

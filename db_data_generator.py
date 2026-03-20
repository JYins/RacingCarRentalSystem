#!/usr/bin/env python3
"""
Mock data generator for the racing car rental database.

The script creates a clean demo dataset with cars, users, orders, returns,
and storage records so the app can be reviewed locally without manual setup.
"""

import random
from datetime import datetime, timedelta
import time

# ============================================================
# DATA DEFINITIONS
# ============================================================

# 20 Luxury Racing Cars
LUXURY_CARS = [
    {"name": "Ferrari 488 GTB", "type": "Sports", "brand": "Ferrari", "color": "Red", "seats": "2", "price": 2500},
    {"name": "Porsche 911 GT3", "type": "Sports", "brand": "Porsche", "color": "White", "seats": "2", "price": 2200},
    {"name": "McLaren 720S", "type": "Sports", "brand": "McLaren", "color": "Orange", "seats": "2", "price": 2800},
    {"name": "Lamborghini Huracan", "type": "Sports", "brand": "Lamborghini", "color": "Yellow", "seats": "2", "price": 2600},
    {"name": "Aston Martin Vantage", "type": "Sports", "brand": "Aston Martin", "color": "British Racing Green", "seats": "2", "price": 2000},
    {"name": "Ferrari F8 Tributo", "type": "Sports", "brand": "Ferrari", "color": "Red", "seats": "2", "price": 2900},
    {"name": "Porsche 918 Spyder", "type": "Hybrid", "brand": "Porsche", "color": "Silver", "seats": "2", "price": 3000},
    {"name": "Mercedes-AMG GT R", "type": "Sports", "brand": "Mercedes", "color": "Green Hell Magno", "seats": "2", "price": 1800},
    {"name": "BMW M8 Competition", "type": "Gran Turismo", "brand": "BMW", "color": "Frozen Black", "seats": "4", "price": 1500},
    {"name": "Audi R8 V10", "type": "Sports", "brand": "Audi", "color": "Nardo Gray", "seats": "2", "price": 1900},
    {"name": "Nissan GT-R Nismo", "type": "Sports", "brand": "Nissan", "color": "Pearl White", "seats": "4", "price": 1600},
    {"name": "Chevrolet Corvette Z06", "type": "Sports", "brand": "Chevrolet", "color": "Rapid Blue", "seats": "2", "price": 1400},
    {"name": "Ford GT", "type": "Supercar", "brand": "Ford", "color": "Liquid Blue", "seats": "2", "price": 2700},
    {"name": "Lamborghini Aventador", "type": "Supercar", "brand": "Lamborghini", "color": "Verde Mantis", "seats": "2", "price": 3000},
    {"name": "McLaren 765LT", "type": "Supercar", "brand": "McLaren", "color": "Papaya Spark", "seats": "2", "price": 2950},
    {"name": "Ferrari SF90 Stradale", "type": "Hybrid", "brand": "Ferrari", "color": "Rosso Corsa", "seats": "2", "price": 2850},
    {"name": "Porsche Taycan Turbo S", "type": "Electric", "brand": "Porsche", "color": "Frozen Blue", "seats": "4", "price": 1700},
    {"name": "Tesla Model S Plaid", "type": "Electric", "brand": "Tesla", "color": "Midnight Silver", "seats": "5", "price": 1200},
    {"name": "Bugatti Chiron", "type": "Hypercar", "brand": "Bugatti", "color": "French Racing Blue", "seats": "2", "price": 3000},
    {"name": "Koenigsegg Jesko", "type": "Hypercar", "brand": "Koenigsegg", "color": "Tang Orange", "seats": "2", "price": 3000},
]

# English names for demo users
FIRST_NAMES = [
    "James", "Michael", "Robert", "David", "William", "Richard", "Joseph", "Thomas", "Christopher", "Charles",
    "Daniel", "Matthew", "Anthony", "Mark", "Donald", "Steven", "Paul", "Andrew", "Joshua", "Kenneth",
    "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Mia", "Charlotte", "Amelia", "Harper", "Evelyn",
    "Lewis", "Max", "Charles", "Sebastian", "Lando", "George", "Carlos", "Fernando", "Kimi", "Nico",
    "Victoria", "Elizabeth", "Margaret", "Catherine", "Alexandra", "Diana", "Caroline", "Natalie", "Grace", "Sarah"
]

LAST_NAMES = [
    "Hamilton", "Verstappen", "Leclerc", "Norris", "Russell", "Sainz", "Alonso", "Raikkonen", "Vettel", "Schumacher",
    "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
    "Anderson", "Taylor", "Thomas", "Hernandez", "Moore", "Martin", "Jackson", "Thompson", "White", "Lopez",
    "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson", "Walker", "Perez", "Hall", "Young",
    "Allen", "Sanchez", "Wright", "King", "Scott", "Green", "Baker", "Adams", "Nelson", "Hill"
]

# Canadian pickup areas used in the sample data
CANADIAN_LOCATIONS = [
    {"region": "Downtown Toronto", "address": "100 Queen St W, Toronto, ON"},
    {"region": "North York", "address": "5000 Yonge St, North York, ON"},
    {"region": "Mississauga", "address": "300 City Centre Dr, Mississauga, ON"},
    {"region": "Vancouver Downtown", "address": "1055 W Georgia St, Vancouver, BC"},
    {"region": "Montreal Centre", "address": "1000 Rue de la Gauchetiere, Montreal, QC"},
    {"region": "Calgary Airport", "address": "2000 Airport Rd NE, Calgary, AB"},
    {"region": "Ottawa Downtown", "address": "150 Elgin St, Ottawa, ON"},
    {"region": "Edmonton", "address": "10065 Jasper Ave, Edmonton, AB"},
]

# ============================================================
# HELPER FUNCTIONS
# ============================================================

def generate_license_plate(index):
    """Generate a Canadian-style license plate"""
    letters = "ABCDEFGHJKLMNPRSTUVWXYZ"
    return f"{random.choice(letters)}{random.choice(letters)}{random.choice(letters)}{random.choice(letters)}{index:03d}"

def generate_phone():
    """Generate a North American phone number"""
    area_codes = ["416", "647", "905", "604", "778", "514", "438", "403", "587", "613"]
    return f"{random.choice(area_codes)}-{random.randint(100,999)}-{random.randint(1000,9999)}"

def generate_id_number():
    """Generate a fake driver's license number"""
    letters = "ABCDEFGHJKLMNPRSTUVWXYZ"
    return f"{random.choice(letters)}{random.randint(1000, 9999)}-{random.randint(10000, 99999)}-{random.randint(10000, 99999)}"

def random_date_in_past_year():
    """Generate a random date within the last year"""
    now = datetime.now()
    days_ago = random.randint(1, 365)
    return now - timedelta(days=days_ago)

def escape_sql(s):
    """Escape single quotes for SQL"""
    if s is None:
        return "NULL"
    return s.replace("'", "''")

def get_timestamp():
    """Get current timestamp in milliseconds for ID generation"""
    return int(time.time() * 1000)

# ============================================================
# SQL GENERATION
# ============================================================

def generate_sql():
    sql_lines = []
    
    # Header
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- Racing Car Rental System - Mock Data")
    sql_lines.append("-- Generated by db_data_generator.py")
    sql_lines.append(f"-- Generated: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    sql_lines.append("-- ============================================================")
    sql_lines.append("")
    sql_lines.append("SET NAMES utf8mb4;")
    sql_lines.append("SET FOREIGN_KEY_CHECKS = 0;")
    sql_lines.append("")
    sql_lines.append("-- Select the database")
    sql_lines.append("USE vehicle_lease_manager;")
    sql_lines.append("")
    
    # TRUNCATE tables (clean slate)
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- TRUNCATE OLD DATA")
    sql_lines.append("-- ============================================================")
    tables_to_truncate = ["car_return", "use_order", "car_dispense", "car_storage", "car_info", "user", "dispense_area", "token"]
    for table in tables_to_truncate:
        sql_lines.append(f"TRUNCATE TABLE `{table}`;")
    sql_lines.append("")
    
    # ============================================================
    # DISPENSE AREAS (Canadian pickup areas)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- DISPENSE AREAS (Canadian Locations)")
    sql_lines.append("-- ============================================================")
    for i, loc in enumerate(CANADIAN_LOCATIONS, start=1):
        sql_lines.append(
            f"INSERT INTO `dispense_area` VALUES ({i}, NOW(), '{escape_sql(loc['region'])}');"
        )
    sql_lines.append("")
    
    # ============================================================
    # CAR INFO (20 luxury cars)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- CAR INFO (20 Luxury Racing Cars)")
    sql_lines.append("-- ============================================================")
    
    car_data = []  # Store for later use
    for i, car in enumerate(LUXURY_CARS, start=1):
        plate = generate_license_plate(i)
        # Use a placeholder image so the sample data stays portable.
        car_name_url = car['name'].replace(' ', '+')
        photo_url = f"https://placehold.co/600x400/222/fff?text={car_name_url}"
        content = f"<p><img src=\"{photo_url}\"></p><p>Premium {car['brand']} rental. {car['type']} class vehicle.</p>"
        
        car_record = {
            "id": i,
            "name": car['name'],
            "type": car['type'],
            "brand": car['brand'],
            "color": car['color'],
            "seats": car['seats'],
            "plate": plate,
            "price": car['price'],
            "content": content
        }
        car_data.append(car_record)
        
        sql_lines.append(
            f"INSERT INTO `car_info` VALUES ({i}, NOW(), '{escape_sql(car['name'])}', "
            f"'{escape_sql(car['type'])}', '{escape_sql(car['brand'])}', '{escape_sql(car['color'])}', "
            f"'{car['seats']}', '{plate}', '{escape_sql(content)}');"
        )
    sql_lines.append("")
    
    # ============================================================
    # CAR DISPENSE (same cars available for rental)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- CAR DISPENSE (Cars Available for Rental)")
    sql_lines.append("-- ============================================================")
    
    for i, car in enumerate(car_data, start=1):
        loc = random.choice(CANADIAN_LOCATIONS)
        # Keep most cars available so the seeded UI looks ready to use.
        status = "Available" if random.random() > 0.2 else "In Use"
        
        sql_lines.append(
            f"INSERT INTO `car_dispense` VALUES ({i}, NOW(), '{escape_sql(car['name'])}', "
            f"'{escape_sql(car['type'])}', '{escape_sql(car['brand'])}', '{car['plate']}', "
            f"'{escape_sql(car['color'])}', '{car['seats']}', {car['price']}, "
            f"'{escape_sql(loc['region'])}', '{escape_sql(loc['address'])}', NOW(), '{status}');"
        )
    sql_lines.append("")
    
    # ============================================================
    # USERS (50 demo users)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- USERS (50 Users with Western Names)")
    sql_lines.append("-- ============================================================")
    
    user_data = []
    used_usernames = set()
    
    for i in range(1, 51):
        first = random.choice(FIRST_NAMES)
        last = random.choice(LAST_NAMES)
        real_name = f"{first} {last}"
        
        # Generate unique username
        base_username = f"{first.lower()}{last.lower()}"
        username = base_username
        counter = 1
        while username in used_usernames:
            username = f"{base_username}{counter}"
            counter += 1
        used_usernames.add(username)
        
        gender = "Male" if first in FIRST_NAMES[:30] else "Female"
        year = random.randint(1970, 2000)
        month = random.randint(1, 12)
        day = random.randint(1, 28)
        birthday = f"{year}-{month:02d}-{day:02d}"
        phone = generate_phone()
        id_num = generate_id_number()
        # Most users are pre-approved so the demo flow is easy to test.
        is_reviewed = "Yes" if random.random() > 0.1 else "No"
        
        user_record = {
            "id": i,
            "username": username,
            "real_name": real_name,
            "phone": phone,
            "is_reviewed": is_reviewed
        }
        user_data.append(user_record)
        
        sql_lines.append(
            f"INSERT INTO `user` VALUES ({i}, NOW(), '{username}', '123456', "
            f"'{escape_sql(real_name)}', '{gender}', '{birthday}', '{id_num}', "
            f"'{phone}', NULL, '{is_reviewed}', NULL);"
        )
    sql_lines.append("")
    
    # ============================================================
    # USE ORDERS (500 orders)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- USE ORDERS (500 Rental Orders)")
    sql_lines.append("-- ============================================================")
    
    order_data = []
    base_id = get_timestamp()
    
    for i in range(500):
        order_id = base_id + i
        user = random.choice(user_data)
        car = random.choice(car_data)
        
        # Random date in last year
        use_time = random_date_in_past_year()
        use_time_str = use_time.strftime('%Y-%m-%d %H:%M:%S')
        
        # Duration 1-168 hours (1-7 days)
        duration_hours = random.randint(1, 168)
        total_price = car['price'] * duration_hours
        
        # Mix finished, active, and unpaid orders for a more useful dashboard.
        rand_status = random.random()
        if rand_status < 0.1:
            # Cancelled - not paid, not returned
            is_paid = "Unpaid"
            is_return = "No"
            is_reviewed = "No"
        elif rand_status < 0.3:
            # Active - paid, not returned yet
            is_paid = "Paid"
            is_return = "No"
            is_reviewed = "Yes"
        else:
            # Finished - paid and returned
            is_paid = "Paid"
            is_return = "Yes"
            is_reviewed = "Yes"
        
        order_record = {
            "id": order_id,
            "car": car,
            "user": user,
            "use_time": use_time,
            "duration": duration_hours,
            "total_price": total_price,
            "is_paid": is_paid,
            "is_return": is_return
        }
        order_data.append(order_record)
        
        sql_lines.append(
            f"INSERT INTO `use_order` VALUES ({order_id}, '{use_time_str}', "
            f"'{escape_sql(car['name'])}', '{escape_sql(car['type'])}', '{escape_sql(car['brand'])}', "
            f"'{car['plate']}', {car['price']}, {duration_hours}, '{total_price}', "
            f"'{use_time_str}', '{user['username']}', '{escape_sql(user['real_name'])}', "
            f"'{user['phone']}', '{is_reviewed}', '', '{is_paid}', '{is_return}');"
        )
    sql_lines.append("")
    
    # ============================================================
    # CAR RETURNS (for finished orders)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- CAR RETURNS (Return Records for Finished Orders)")
    sql_lines.append("-- ============================================================")
    
    return_id = get_timestamp() + 10000
    for order in order_data:
        if order['is_return'] == 'Yes':
            return_time = order['use_time'] + timedelta(hours=order['duration'])
            return_time_str = return_time.strftime('%Y-%m-%d %H:%M:%S')
            car = order['car']
            user = order['user']
            
            sql_lines.append(
                f"INSERT INTO `car_return` VALUES ({return_id}, '{return_time_str}', "
                f"'{escape_sql(car['name'])}', '{escape_sql(car['type'])}', '{escape_sql(car['brand'])}', "
                f"'{car['plate']}', '{return_time_str}', 'Returned in good condition.', "
                f"'{user['username']}', '{escape_sql(user['real_name'])}', '{user['phone']}', "
                f"'Yes', 'Approved');"
            )
            return_id += 1
    sql_lines.append("")
    
    # ============================================================
    # CAR STORAGE (some cars in storage)
    # ============================================================
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- CAR STORAGE (Cars in Maintenance/Storage)")
    sql_lines.append("-- ============================================================")
    
    # Put a few random cars in storage
    storage_cars = random.sample(car_data, 5)
    for i, car in enumerate(storage_cars, start=1):
        loc = random.choice(CANADIAN_LOCATIONS)
        sql_lines.append(
            f"INSERT INTO `car_storage` VALUES ({i}, NOW(), '{escape_sql(car['name'])}', "
            f"'{escape_sql(car['type'])}', '{escape_sql(car['brand'])}', '{car['plate']}', "
            f"'{escape_sql(loc['address'])}', NOW());"
        )
    sql_lines.append("")
    
    # Footer
    sql_lines.append("SET FOREIGN_KEY_CHECKS = 1;")
    sql_lines.append("")
    sql_lines.append("-- ============================================================")
    sql_lines.append("-- DATA GENERATION COMPLETE!")
    sql_lines.append(f"-- Users: 50")
    sql_lines.append(f"-- Cars: 20")
    sql_lines.append(f"-- Orders: 500")
    sql_lines.append(f"-- Locations: {len(CANADIAN_LOCATIONS)}")
    sql_lines.append("-- ============================================================")
    
    return "\n".join(sql_lines)

# ============================================================
# MAIN
# ============================================================

if __name__ == "__main__":
    print("=" * 60)
    print("Racing Car Rental System - Mock Data Generator")
    print("=" * 60)
    print()
    
    print("Generating SQL data...")
    sql_content = generate_sql()
    
    output_file = "insert_mock_data.sql"
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(sql_content)
    
    print(f"Generated {output_file}")
    print()
    print("Summary:")
    print("  - 50 demo users")
    print("  - 20 luxury racing cars")
    print("  - 500 rental orders")
    print("  - 8 Canadian locations")
    print()
    print("To import the data, run:")
    print(f"  mysql -u root -p vehicle_lease_manager < {output_file}")
    print()
    print("Done!")


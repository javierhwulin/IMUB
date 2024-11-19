# **imUB**

**A movie and series management application with an intuitive GUI and database integration**
*Developed as a student project for the Bachelor's in Computer Engineering*
*Faculty of Mathematics and Computer Science, UB*

------

## **1. Descripció**

**imUB** is a student-developed project that combines modern software design principles with a practical implementation of a movie and series management system. The application features:

- A comprehensive list of available digital content (movies and series).
- A personalized WishList for users to track desired content.
- A dynamic Top 10 ranking of the best-rated content, updated automatically.

This project focuses on implementing an intuitive **Graphical User Interface (GUI)** using **JavaFX** and an integrated **SQLite database**. It serves as a practical exercise to explore the **Model-View-Controller (MVC)** architecture, database interactions, and real-time interface updates.

The project also emphasizes hands-on learning of:

- Efficient GUI design.
- Real-time data synchronization with a database.
- Dynamic rankings based on user ratings and preferences.

Ideal for exploring both the theoretical and practical aspects of software development, **imUB** highlights how software layers work together to create a functional and user-friendly application.

### **Features and Functionalities:**

1. **Content Management:**
    - Displays a categorized list of movies and series available in the database.
    - Allows sorting by name, type (movies/series), and theme (future implementation).
2. **WishList:**
    - A personalized list for users to save and track movies or series they plan to watch.
    - Automatically updates as content is added or removed.
3. **Top 10 Rankings:**
    - Dynamic rankings of the most popular content, calculated based on average or weighted user ratings.
    - Users can view rankings based on different criteria, such as likes/dislikes or star ratings.
4. **Real-Time Updates:**
    - Ratings and WishList changes are reflected immediately without requiring manual refreshes.
5. **Secure Login System:**
    - Users must log in with credentials stored in the database to access the application.
    - The system supports multiple users, with unique profiles and preferences.

------

## **2. Instal·lació**

Follow these steps to set up and run the **imUB** application:

### **Step 1: Configure Java and JavaFX**

- Ensure that you have **Java 17** installed to compile and run the application with JavaFX support.
- Update your environment to include JavaFX dependencies.

### **Step 2: Install Dependencies**

- Open the project in **IntelliJ IDEA**.
- Right-click on the `pom.xml` file, navigate to the Maven menu, and select "Reload Project" to install all required dependencies.

### **Step 3: Configure Database Access**

- Use the IntelliJ Ultimate Edition (student license) to enable database tools.
- Open the `data.sqlite` file from the project’s `Data` folder to view and manage the database contents. Refer to Appendix B for details.

### **Step 4: Run the Application**

- Run the `AppMain` class to start the application.
- Log in using credentials from the `Persona` table in the database. Example users are preloaded for testing purposes.



## **Apèndix A: Part de la Vista gràfica: GUI**

### **A.1. Scene Management in JavaFX**

- The project uses JavaFX `.fxml` files for the visual layout of windows.
- Each `.fxml` file is linked to a corresponding Java controller class via the `fx:controller` attribute.
- The `EscenaFactory` class simplifies creating new scenes dynamically.

### **A.2. Using SceneBuilder**

- Download and install a SceneBuilder tool to edit .fxml files visually. Options include:
    - [Oracle SceneBuilder](https://www.oracle.com/java/technologies/javase/javafxscenebuilder-info.html)
    - [Gluon SceneBuilder](https://gluonhq.com/products/scene-builder/)
- Right-click on an `.fxml` file in IntelliJ and select "Open in SceneBuilder" to edit its layout.



## **Apèndix B: Manual de la Base de Dades**

### **B.1. Viewing and Editing the Database**

- Use IntelliJ’s Database View to explore and modify the SQLite database (`data.sqlite`).
- Add new records or update existing ones to reflect changes in the application. Refer to screenshots for step-by-step guidance.

### **B.2. Synchronizing Changes**

- After making database modifications:
    1. Stop any running instance of the application.
    2. Update tables as needed, ensuring all required fields are populated.
    3. Save changes and restart the application to see the updates reflected.



## **Apèndix C: Problemes d'Instal·lació**

If the application fails to run, follow these troubleshooting steps:

1. **Dependency Issues:**
    - Ensure Maven dependencies are fully loaded by reloading the `pom.xml` file.
    - Check for errors in the `AppMain` file. If issues persist, reload Maven dependencies again.
2. **JavaFX Errors:**
    - Missing JavaFX dependencies can result in runtime errors. Reload Maven or manually verify that all JavaFX libraries are correctly configured.
3. **Database Errors:**
    - Ensure the SQLite database file (`data.sqlite`) is present in the expected directory.
    - Use the IntelliJ Database Viewer to validate database connectivity.
4. **General Issues:**
    - Perform a fresh pull of the project repository and repeat the installation steps.
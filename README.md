# CloudNote - Cloud-based Note Taking Platform

## Background and Objectives

With the increasing popularity of paperless learning and work methods, there is a growing demand for lightweight and efficient note-taking products. CloudNote aims to address this demand by providing a streamlined and user-friendly cloud-based note-taking platform. The main objectives of this project are as follows:

- Build an easy-to-use platform for note management and sharing.
- Provide seamless and efficient note-writing capabilities.
- Enhance data security and privacy.
- Improve efficiency in note organization and management for learning and work purposes.

## Key Features

<img width="413" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/f33d6f8d-4da2-4c46-bc22-320e794c7d95">

### User Module

1. **User Registration and Login**: Users can register by providing their phone number and password. The registration process includes human verification for security purposes.
2. **Profile Management**: Users can modify their profile information such as avatar, nickname, and password.

### Notebook Group Module

1. **Create Notebook Groups**: Users can create an unlimited number of notebook groups organized in a tree-like directory structure, allowing easy management and indent-based numbering.
2. **Delete Notebook Groups**: Users can delete notebook groups, which will move the deleted groups, their subgroups, and associated notes to the recycle bin.
3. **Modify Notebook Groups**: Users can modify the category and name of notebook groups.

### Note Module

1. **Note Editing**: Users can write notes using a rich text editor or a Markdown editor. The Markdown editor includes a sidebar outline preview for easy navigation.
2. **Create Notes**: Users can create new notes directly within the platform or upload existing files in formats such as Markdown, Word, and PDF for editing.
3. **Delete Notes**: Deleted notes are moved to the recycle bin.
4. **Modify Notes**: Editing notes creates a new version, allowing users to track changes and access previous versions.
5. **Import Templates**: Users can import system templates or create their own templates to improve writing efficiency.

### Search Module

1. **Note Search**: Users can search for notes containing specific keywords, and the platform displays a list of matching notes.

### Recycle Bin

1. **Overview**: The recycle bin displays deleted notebook groups and notes.
2. **Permanent Deletion**: Users can permanently delete notebook groups and notes from the recycle bin.
3. **Restore**: Users can restore notebook groups and notes from the recycle bin to their original locations.

### Version History

Users can view the complete history of note revisions, including content differences and modification timestamps.

### Sharing Functionality

1. **Share on Social Media**: Users can share notes on popular social media platforms such as QQ, Weibo, and WeChat.
2. **Generate Share QR Code**: Users can generate a QR code for easy mobile access to shared notes.

### Favorites Module

1. **View Favorites**: Users can view their list of favorite notes and directly edit and modify them.

### Tag Module

1. **Add Tags**: Users can add multiple tags to notes for better organization and retrieval.
2. **Tag Search**: Users can search for related articles using specific tags.

### Tools

1. **Online Translation of Note Fragments**: In reading mode, users can translate note fragments into multiple languages online.
2. **Export to PDF and Word**: Users can export notes as PDF or Word documents.
3. **Print Notes**: Users can print notes directly from the platform.

## Screenshots

<img width="416" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/1e64d093-ab6d-4cf6-b1ee-96db2ea20f8f">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/4893e21e-e8f9-4faa-be0f-135d2dc39ea7">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/987fbd64-6edb-4f9e-923e-c77ebcf0dc53">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/71438abe-9ce7-4a1a-bb63-7e427f90e119">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/52851933-9761-46fa-8b65-131bdad463f4">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/c595ffbf-4d6f-4ca2-a684-90ea0e67caec">
<img width="415" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/158082f7-9b2c-47c3-9205-0d1110b8789c">
<img width="416" alt="image" src="https://github.com/sonichen/CloudNote/assets/57282299/914228a8-07a8-423c-b650-1d35902eea7c">



## Innovative Features

1. **User-Friendly**: The platform offers a tree-like directory structure for notebook groups, making them easy to manage. Users can choose between a rich text editor and a Markdown editor. The platform also provides online translation for note fragments and allows users to access revision history and the recycle bin.
2. **Easy Writing**: Users can utilize both a rich text editor and a Markdown editor. They can upload existing files for online editing and customize templates for improved writing efficiency.
3. **Easy Sharing**: The platform supports sharing notes on popular social media platforms and provides QR codes for easy mobile access to shared content.
4. **Data Security**: The platform utilizes IPFS (InterPlanetary File System) for storing note content and image information, ensuring data permanence, decentralization, and protection against tampering.



## Development Environment

### Frontend

- Runtime Environment: Yarn 1.22.17, Node.js v14.16.1, Vue-cli 2.9.6
- Frontend Technologies: Vue 2.6.11, ElementUI2
- Development Tools: VSCode 1.67

### Backend

- Runtime Environment: Java 11, MySQL 8.0, IPFS 0.12.2, Go 1.18.1 (for IPFS configuration)
- Backend Technologies: Java, Spring Boot, MyBatis framework, IPFS storage
- Development Tools: IDEA 2021.2.1, Power Designer 16.5



## Contributors

- Backend and project management: sonichen 
- Frontend and UI: chengtianxi



## Conclusion

Future plans for CloudNote include enhancing existing features such as adding a comment section and read password protection for shared notes, implementing collaborative real-time editing, and supporting additional document formats such as mind maps. The ultimate goal is to leverage IPFS for storing large amounts of text and image data and store all business logic on the Ethereum blockchain to create a fully decentralized application.



## Contributing

Contributions to the project are welcome. If you encounter any issues or have suggestions for improvements, please open an issue in the project repository.

When contributing code, make sure to follow the existing code style and create a descriptive pull request explaining the changes you made.

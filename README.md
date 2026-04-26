# Articlux — Project Documentation

## Overview

Articlux is a modern Android application designed to help users consume, review, and interact with curated articles in an intuitive and visually engaging way. Built using Jetpack Compose, the app emphasizes clean UI, modular architecture, and responsive design.

The application enables users to browse articles, explore categorized content, and provide ratings or reviews.

---

## Features

### Article Feed
- Displays a list or grid of articles  
- Supports dynamic content loading via API  
- Card-based UI for better readability  

### Search and Discovery
- Search articles by keywords or titles  
- Category-based filtering (e.g., Knowledge Nuggets, Ethics Simplified)  

### Review and Rating System
- Users can rate articles  
- Dedicated review screen  
- Structured UI for feedback interaction  

### Article Summarization
- Each article can be summarized using NotebookLM  
- Opens externally in Chrome for processing and viewing summaries  

### Responsive UI
- Optimized for both mobile and tablet devices  
- Adaptive layouts using Jetpack Compose  

---

## Tech Stack

- **Language:** Kotlin  
- **UI Framework:** Jetpack Compose  
- **Architecture:** MVVM  
- **Networking:** Retrofit  
- **Asynchronous Programming:** Kotlin Coroutines  

---

## API Integration

This project works with the News Handler API:

https://github.com/1-noob/news_handler

- Retrofit is used for network communication  
- Supports dynamic URLs  
- JSON parsing handled via converters  

Example:

```kotlin
@GET
suspend fun getArticles(@Url url: String): List<Article>
```

---

## Setup and Installation

### Prerequisites
- Android Studio (latest stable version)  
- Android SDK installed  
- Kotlin support enabled  

### Installation Steps

```bash
git clone https://github.com/your-username/articlux.git
cd articlux
```

1. Open the project in Android Studio  
2. Sync Gradle  
3. Run the app on an emulator or physical device  

---

## Limitations

- The News Handler API is not hosted on a public server.  
- You must manually update the **base URL** in the code to match your local or deployed instance of the API.  
- Article summarization currently depends on NotebookLM and opens externally in a browser.  

---

## Future Improvements

- Integrate a local LLM for on-device or in-app summarization  
- Enable in-app summary generation without external browser dependency  
- Provide downloadable PDF versions of articles and summaries  
- Improve overall performance and offline capabilities
- Store all summaries on an Obsidian Vault
- Improve app UI
- Expand it to cover more news categories   

--- 

# viper-architecture

![Build](https://github.com/sukminkang12/viper-architecture/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

## VIPER architecture for 'OWWL'
![architecture](https://user-images.githubusercontent.com/39121338/140265801-cdb40c8c-d62f-460a-8f65-fbf68b20aedf.jpg)

## Why i made it
  In the past, each time the part contained in the red rectangular was composed (for each screen), a total of 4 files had to be produced. - Activity/Fragment, Presenter, Protocol, Router -
  It was cumbersome to create 4 files each time, and there was a risk of human error.
  So, I wrote boilerplate code for OWWL's own architecture and implemented it as a template plugin of Android Studio.
  With this, I no longer have to look at the old code every time you build a screen!

## Will update
  1. Only current Activity are supported. I will improve it to support fragment as well.
  2. Icon   

## Installation
  Download the [latest release](https://github.com/sukminkang12/viper-architecture/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

<!-- Plugin description -->

VIPER architecture template plugin for OWWL

<!-- Plugin description end -->

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

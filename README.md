# About

`react-native-navigation-bar-style` based on `react-native-navigation-bar-color`.

only test on `react-native@0.60.5`

# Required

Android 5.0+, API Version 21+

# Usage

```
import NavigationBarStyle from 'react-ntive-navigation-bar-style'

// interface like StatusBar

NavigationBarStyle.setBackgroundColor('#AARRGGBB') // unsupport "transparent"

// true: over layout, false: bottom layout. be careful navigation-bar width(landscape,left or right side) or height(portrait, bottom)
NavigationBarStyle.setTranslucent(bool)

// "light-content" or "dark-content"
NavigationBarStyle.setBarStyle(string)

NavigationBarStyle.setHidden(bool)

NavigationBarStyle.getSystemUiVisibility().then(flags: int => {
	// todo
})

NavigationBarStyle.setSystemUiVisibility(flags: int) // be careful timing

```
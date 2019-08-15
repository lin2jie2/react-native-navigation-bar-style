
import { NativeModules } from 'react-native';

const { RNNavigationBarStyle } = NativeModules;

const {
	setLightNav,
	setTranslucent,
	setBackgroundColor,
	setHidden,
	getSystemUiVisibility,
	setSystemUiVisibility,
} = RNNavigationBarStyle

export default {
	setHidden,
	setBarStyle: (style) => {
		if (style == 'light-content') {
			setLightNav(true)
		}
		else if (style == 'dark-content') {
			setLightNav(false)
		}
	},
	setTranslucent,
	setBackgroundColor,
	getSystemUiVisibility,
	setSystemUiVisibility,
}

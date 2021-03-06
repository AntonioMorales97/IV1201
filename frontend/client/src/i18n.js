import i18n from 'i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import XHR from 'i18next-xhr-backend';
import translationEng from './locales/en/translation.json';
import translationSwe from './locales/se/translation.json';
import { initReactI18next } from 'react-i18next';

/**
 * Configuration of the internationalization in this SPA.
 */
i18n
  .use(XHR)
  .use(LanguageDetector)
  .use(initReactI18next)
  .init({
    debug: true,
    lng: 'en',
    fallbackLng: 'en',

    interpolation: {
      escapeValue: false
    },

    resources: {
      en: {
        translations: translationEng
      },
      se: {
        translations: translationSwe
      }
    },
    ns: ['translations'],
    defaultNS: 'translations',

    react: {
      wait: true
    }
  });

export default i18n;

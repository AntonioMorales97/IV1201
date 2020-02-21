import { diff } from 'json-diff';
import en from './en/translation.json';
import se from './se/translation.json';

const missingKeys = {
  home: 'Home'
};

/**
 * Should test all the locales, i.e. all the translation.json:s.
 * Note:
 * { <key>__deleted: <old value> } when the second object is missing a key
 *  { <key>__added: <new value> } when the first object is missing a key
 *  undefined with a recursive diff for two objects with diffent values for a key
 */
describe('Running locales test', () => {
  it('it should return undefined with a recursive diff for two JSONs that has the same keys (not necessarily same values)', () => {
    expect(diff(en, se, { keysOnly: true })).toBeUndefined();
  });

  it('should return { <key>_deleted: <old_value> } when the second JSON is missing a key. Should return { <key>__added: <new value> } when the first JSON is missing a key', () => {
    expect(diff(en, missingKeys, { keysOnly: true })).toBeDefined();
  });
});

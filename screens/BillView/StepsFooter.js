import React from 'react'
import { StyleSheet, View } from 'react-native'
import { Button } from '@rneui/themed'

export default function StepsFooter() {
  return (
    <View style={styles.footer}>
      <Button title="Back" size="lg" type="clear" />
      <Button title="Next" size="lg" type="clear" />
    </View>
  )
}

const styles = StyleSheet.create({
  footer: {
    width: '100%',
    paddingHorizontal: 20,
    paddingBottom: 20,
    position: 'absolute',
    bottom: 0,
    justifyContent: 'space-between',
    flexDirection: 'row',
  },
})

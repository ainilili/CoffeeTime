const {app, BrowserWindow} = require('electron')
const path = require('path')
const url = require('url')

// ����һ������ window �����ȫ�����ã�����㲻��������
// �� JavaScript �����������գ� window �ᱻ�Զ��عر�
let win

function createWindow () {

  // ������������ڡ�
  win = new BrowserWindow({width: 1300, height: 800})

  // Ȼ�����Ӧ�õ� index.html��
  win.loadURL(url.format({
    pathname: path.join(__dirname, 'index.html'),
    protocol: 'file:',
    slashes: true
  }))

  // �򿪿����߹��ߡ�
  // win.webContents.openDevTools()

  // �� window ���رգ�����¼��ᱻ������
  win.on('closed', () => {
    // ȡ������ window ����������Ӧ��֧�ֶര�ڵĻ���
    // ͨ����Ѷ�� window ��������һ���������棬
    // ���ͬʱ����Ӧ��ɾ����Ӧ��Ԫ�ء�
    win = null
  })
}

// Electron ���ڳ�ʼ����׼��
// �������������ʱ���������������
// ���� API �� ready �¼����������ʹ�á�
app.on('ready', createWindow)

// ��ȫ�����ڹر�ʱ�˳���
app.on('window-all-closed', () => {
  // �� macOS �ϣ������û��� Cmd + Q ȷ�����˳���
  // ������󲿷�Ӧ�ü���˵����ᱣ�ּ��
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  // ��macOS�ϣ�������dockͼ�겢��û���������ڴ�ʱ��
  // ͨ����Ӧ�ó��������´���һ�����ڡ�
  if (win === null) {
    createWindow()
  }
})

// �����ļ����������дӦ��ʣ�������̴��롣
// Ҳ���Բ�ֳɼ����ļ���Ȼ���� require ���롣
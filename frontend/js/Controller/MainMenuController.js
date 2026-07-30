
export class MainMenuController {
    /**
     * @param {Object} options
     * @param {string} options.wsUrl
     * @param {Object.<string, HTMLElement>} options.screens 
     */
    constructor({ wsUrl, screens = {} }) {
        this.wsUrl = wsUrl;
        this.screens = screens;
        this.socket = null;

        this._connect();
    }
    _connect() {
        this.socket = new WebSocket(this.wsUrl);

        this.socket.addEventListener('open', () => {
            console.log('Đã kết nối WebSocket tới', this.wsUrl);
        });

        this.socket.addEventListener('message', (event) => {
            this._handleServerMessage(event.data);
        });

        this.socket.addEventListener('close', () => {
            console.log('WebSocket đã đóng');
        });

        this.socket.addEventListener('error', (err) => {
            console.error('Lỗi WebSocket:', err);
        });
    }
    _handleServerMessage(rawData) {
        let data;
        try {
            data = JSON.parse(rawData);
        } catch (e) {
            console.warn('Message không phải JSON hợp lệ:', rawData);
            return;
        }

        switch (data.type) {
            case 'screen':
                this.showScreen(data.name);
                break;
            case 'info':
                console.log('[Server info]', data.message);
                break;
            case 'closed':
                console.log('Server xác nhận đóng kết nối (Quit)');
                break;
            default:
                console.warn('Không rõ loại message:', data);
        }
    }

    showScreen(name) {
        const target = this.screens[name];
        if (!target) {
            console.warn(`Không tìm thấy màn hình "${name}"`);
            return;
        }
        Object.values(this.screens).forEach((el) => {
            el.style.display = 'none';
        });
        target.style.display = 'block';
    }

    /**
     * @param {string} command 
     */
    handleAction(command) {
        if (!this.socket || this.socket.readyState !== WebSocket.OPEN) {
            console.warn('WebSocket chưa sẵn sàng, không gửi được action:', command);
            return;
        }
        this.socket.send(JSON.stringify({ action: command }));
    }
}